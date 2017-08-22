package com.longyg.frontend.model.param;

import com.longyg.frontend.model.ne.NeRelease;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NeParamRepository extends MongoRepository<NeParam, String> {
    NeParam findByNe(NeRelease ne);
    void deleteByNe(NeRelease ne);
}
