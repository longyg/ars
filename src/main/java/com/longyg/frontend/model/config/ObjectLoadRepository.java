package com.longyg.frontend.model.config;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ObjectLoadRepository extends MongoRepository<ObjectLoad, String> {
}
