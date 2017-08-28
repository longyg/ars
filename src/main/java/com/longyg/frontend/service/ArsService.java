package com.longyg.frontend.service;

import com.longyg.frontend.model.ars.ArsConfig;
import com.longyg.frontend.model.ars.ArsConfigRepository;
import com.longyg.frontend.model.ne.NeRelease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArsService {
    @Autowired
    private ArsConfigRepository arsConfigRepository;

    public ArsConfig findArsConfig(NeRelease neRelease) {
        if (null == neRelease) {
            return null;
        }
        return arsConfigRepository.findByNeTypeAndRelease(neRelease.getNeType(), neRelease.getNeVersion());
    }
}
