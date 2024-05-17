package src.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.SessionSynchronization;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableMongoRepositories(basePackages = {"src.repository_one"},
    mongoTemplateRef = "entityOneMongoTemplate")
public class DatabaseOneConfig {
    @Primary
    @Bean(name = "entityOneProperties")
    @ConfigurationProperties(prefix = "spring.data.entity.one")
    public MongoProperties getEntityOneProps() {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "entityOneMongoTemplate")
    public MongoTemplate entityOneMongoTemplate() throws Exception {
        MongoTemplate template = new MongoTemplate(entityOneMongoDatabaseFactory(getEntityOneProps()));
//        template.setSessionSynchronization(SessionSynchronization.ALWAYS);
        return template;
    }

    @Primary
    @Qualifier("entityOneMongoDatabaseFactory")
    @Bean
    public MongoDatabaseFactory entityOneMongoDatabaseFactory(MongoProperties mongo) throws Exception {
        return new SimpleMongoClientDatabaseFactory(
                mongo.getUri()
        );
    }

    @Primary
    @Qualifier("databaseOneTransactionManager")
    @Bean
    MongoTransactionManager databaseOneTransactionManager(@Qualifier("entityOneMongoDatabaseFactory") MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}
