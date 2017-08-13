package com.longyg.frontend.model.ars.us;

import com.longyg.frontend.model.ne.NetworkElement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsRepository extends MongoRepository<UserStorySpec, String> {
    UserStorySpec findByNe(NetworkElement ne);
    void deleteByNe(NetworkElement ne);
}
