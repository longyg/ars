package com.longyg.frontend.model.config;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ObjectRepository extends MongoRepository<GlobalObject, String> {
}
