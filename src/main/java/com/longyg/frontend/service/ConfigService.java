package com.longyg.frontend.service;

import com.longyg.frontend.model.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

@Component
public class ConfigService {
    @Autowired
    private AdaptationResourceRepository resourceRepository;

    @Autowired
    private InterfaceRepository interfaceRepository;

    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    private LoadConfigRepository loadConfigRepository;

    public AdaptationResource findResource(String id) {
        if (null == id) {
            return null;
        }
        Optional<AdaptationResource> opt = resourceRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    public List<AdaptationResource> findResources() {
        return resourceRepository.findAll();
    }

    public AdaptationResource saveResource(AdaptationResource resource) {
        return resourceRepository.save(resource);
    }

    public List<InterfaceObject> findInterfaces() {
        return interfaceRepository.findAll();
    }

    public InterfaceObject findInterface(String id) {
        if (null == id) {
            return null;
        }
        Optional<InterfaceObject> opt = interfaceRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    public List<GlobalObject> findGlobalObjects() {
        return objectRepository.findAll();
    }

    public List<LoadConfig> findLoadConfigs(String neTypeId) {
        if (null == neTypeId) {
            return new ArrayList<>();
        }
        return loadConfigRepository.findByNeTypeId(neTypeId);
    }
}
