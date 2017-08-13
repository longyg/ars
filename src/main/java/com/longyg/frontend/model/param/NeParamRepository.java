package com.longyg.frontend.model.param;

import com.longyg.frontend.model.ne.NetworkElement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NeParamRepository extends MongoRepository<NeParam, String> {
    NeParam findByNe(NetworkElement ne);
    void deleteByNe(NetworkElement ne);
}
