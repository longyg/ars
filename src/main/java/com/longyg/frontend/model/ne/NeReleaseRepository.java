package com.longyg.frontend.model.ne;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NeReleaseRepository extends MongoRepository<NeRelease, String> {
    List<NeRelease> findByNeType(String neType);
}
