package com.longyg.frontend.service;

import com.longyg.frontend.model.ars.ARS;
import com.longyg.frontend.model.ars.ArsConfig;
import com.longyg.frontend.model.ars.ArsConfigRepository;
import com.longyg.frontend.model.ars.ArsRepository;
import com.longyg.frontend.model.ars.om.ObjectModelSpec;
import com.longyg.frontend.model.ars.om.OmRepository;
import com.longyg.frontend.model.ars.us.UsRepository;
import com.longyg.frontend.model.ars.us.UserStorySpec;
import com.longyg.frontend.model.ne.NeRelease;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArsService {
    @Autowired
    private ArsConfigRepository arsConfigRepository;

    @Autowired
    private UsRepository usRepository;

    @Autowired
    private OmRepository omRepository;

    @Autowired
    private ArsRepository arsRepository;

    public ArsConfig findArsConfig(NeRelease neRelease) {
        if (null == neRelease) {
            return null;
        }
        return arsConfigRepository.findByNeTypeAndRelease(neRelease.getNeType(), neRelease.getNeVersion());
    }

    public ArsConfig saveConfig(ArsConfig arsConfig) {
        return arsConfigRepository.save(arsConfig);
    }

    public ARS saveArs(ARS ars) {
        return arsRepository.save(ars);
    }

    public UserStorySpec saveUs(UserStorySpec usSpec) {
        return usRepository.save(usSpec);
    }

    public ObjectModelSpec saveObjectModel(ObjectModelSpec omSpec) {
        return omRepository.save(omSpec);
    }
}
