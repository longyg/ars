package com.longyg.frontend.model.ne;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface NeTypeRepository extends MongoRepository<NeType, String> {
    NeType findByName(String name);
}
