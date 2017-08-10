package com.longyg.frontend.model.ne;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NERepository extends MongoRepository<NetworkElement, String> {

}
