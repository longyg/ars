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
    private ObjectLoadRepository objectLoadRepository;

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

    public List<ObjectLoad> findObjectLoads() {
        return objectLoadRepository.findAll();
    }

    public ObjectLoad saveObjectLoad(ObjectLoad objectLoad) {
        return objectLoadRepository.save(objectLoad);
    }

    public ObjectLoad findObjectLoad(String id) {
        if (null == id)
            return null;
        Optional<ObjectLoad> opt = objectLoadRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    public List<ObjectLoad> findObjectLoads(List<String> ids) {
        List<ObjectLoad> loads = new ArrayList<>();
        for (String id : ids) {
            ObjectLoad load = findObjectLoad(id);
            if (null != load && !loads.contains(load)) {
                loads.add(load);
            }
        }
        return loads;
    }

    public void deleteLoad(String id) {
        if (null == id) {
            return;
        }
        objectLoadRepository.deleteById(id);
    }
}
