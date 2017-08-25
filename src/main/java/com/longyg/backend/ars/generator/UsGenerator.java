package com.longyg.backend.ars.generator;

import com.longyg.frontend.model.ars.ArsConfig;
import com.longyg.frontend.model.param.NeParam;
import com.longyg.frontend.model.param.NeParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.logging.Logger;

@Component
public class UsGenerator {
    private static final Logger LOG = Logger.getLogger(UsGenerator.class.getName());

    private ArsConfig config;

    @Autowired
    private NeParamRepository neParamRepository;

    public String generateAndSave(ArsConfig config) {
        this.config = config;

        NeParam neParam = getNeParam();

        if (null == neParam) {
            LOG.severe("NE parameters are not set");
            return null;
        }



        return null;
    }

    private NeParam getNeParam() {
        if (config.getNeParamId() != null) {
            Optional<NeParam> opt = neParamRepository.findById(config.getNeParamId());
            if (opt.isPresent()) {
                return opt.get();
            }
        }
        return null;
    }
}
