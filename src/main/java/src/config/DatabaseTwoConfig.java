package src.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.SessionSynchronization;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableMongoRepositories(basePackages = {"src.repository_two"},
    mongoTemplateRef = "entityTwoMongoTemplate")
public class DatabaseTwoConfig {
    @Bean(name = "entityTwoProperties")
    @ConfigurationProperties(prefix = "spring.data.entity.two")
    public MongoProperties getEntityTwoProps() {
        return new MongoProperties();
    }

    @Bean(name = "entityTwoMongoTemplate")
    public MongoTemplate entityTwoMongoTemplate() throws Exception {
        MongoTemplate template = new MongoTemplate(entityTwoMongoDatabaseFactory(getEntityTwoProps()));
//        template.setSessionSynchronization(SessionSynchronization.ALWAYS);
        return template;
    }

    @Qualifier("entityTwoMongoDatabaseFactory")
    @Bean
    public MongoDatabaseFactory entityTwoMongoDatabaseFactory(MongoProperties mongo) throws Exception {
        return new SimpleMongoClientDatabaseFactory(
                mongo.getUri()
        );
    }

    @Qualifier("databaseTwoTransactionManager")
    @Bean
    MongoTransactionManager databaseTwoTransactionManager(@Qualifier("entityTwoMongoDatabaseFactory") MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}
