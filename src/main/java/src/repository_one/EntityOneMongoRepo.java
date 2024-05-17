package src.repository_one;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityOneMongoRepo extends MongoRepository<EntityOne, String> {
}
