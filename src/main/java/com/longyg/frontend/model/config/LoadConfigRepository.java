package com.longyg.frontend.model.config;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LoadConfigRepository extends MongoRepository<LoadConfig, String> {
    List<LoadConfig> findByNeTypeId(String neTypeId);
}
