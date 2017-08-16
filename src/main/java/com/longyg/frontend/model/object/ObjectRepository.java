package com.longyg.frontend.model.object;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ObjectRepository extends MongoRepository<GlobalObject, String> {
}
