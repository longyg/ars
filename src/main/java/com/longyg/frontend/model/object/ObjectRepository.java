package com.longyg.frontend.model.object;

import com.longyg.backend.adaptation.topology.PmbObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ObjectRepository extends MongoRepository<PmbObject, String> {
}
