package src.repository_two;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityTwoMongoRepo extends MongoRepository<EntityTwo, String> {
}
