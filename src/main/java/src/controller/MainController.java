package src.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;
import src.repository_one.EntityOne;
import src.repository_one.EntityOneMongoRepo;
import src.repository_two.EntityTwo;
import src.repository_two.EntityTwoMongoRepo;

@RestController
@RequestMapping(value = "/api")
public class MainController {
    @Autowired
    EntityOneMongoRepo entityOneMongoRepo;

    @Autowired
    EntityTwoMongoRepo entityTwoMongoRepo;

    @Autowired
    @Qualifier("entityOneMongoTemplate")
    MongoTemplate entityOneMongoTemplate;

    @Autowired
    @Qualifier("entityTwoMongoTemplate")
    MongoTemplate entityTwoMongoTemplate;

    @GetMapping(value = "/entity-one")
    public ResponseEntity getAllEntityOne() {
        return ResponseEntity.ok(entityOneMongoRepo.findAll());
    }

    @GetMapping(value = "/entity-two")
    public ResponseEntity getAllEntityTwo() {
        return ResponseEntity.ok(entityTwoMongoRepo.findAll());
    }

    @PutMapping(value = "/entity-one")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity createEntityOne(@RequestBody EntityOne request) throws Exception {
        entityOneMongoRepo.save(request);
        throw new Exception("test");
    }

    @PutMapping(value = "/entity-two")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity createEntityTwo(@RequestBody EntityTwo request) throws Exception {
        entityTwoMongoRepo.save(request);
        throw new Exception("test");
    }
}
