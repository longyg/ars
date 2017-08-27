package com.longyg.frontend.model.config;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface InterfaceRepository extends MongoRepository<InterfaceObject, String> {
}
