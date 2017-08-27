package com.longyg.backend.ars.generator;

import com.longyg.frontend.model.ars.ARS;
import com.longyg.frontend.model.ars.ArsConfig;
import com.longyg.frontend.model.ars.ArsRepository;
import com.longyg.frontend.model.config.AdaptationResource;
import com.longyg.frontend.model.config.AdaptationResourceRepository;
import com.longyg.frontend.model.config.InterfaceObject;
import com.longyg.frontend.model.config.InterfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class ArsGenerator {
    private static final Logger LOG = Logger.getLogger(ArsGenerator.class.getName());

    private ArsConfig config;

    @Autowired
    private InterfaceRepository interfaceRepository;
    @Autowired
    private AdaptationResourceRepository resourceRepository;

    @Autowired
    private ArsRepository arsRepository;

    @Autowired
    private UsGenerator usGenerator;

    @Autowired
    private ObjectModelGenerator omGenerator;

    @Autowired
    private PmDataLoadGenerator pmDataLoadGenerator;

    @Autowired
    private CounterGenerator counterGenerator;

    @Autowired
    private AlarmGenerator alarmGenerator;

    public ARS generateAndSave(ArsConfig config) {
        this.config = config;

        String usId = usGenerator.generateAndSave(config);
        String omId = omGenerator.generateAndSave(config);
        String pmDlId = pmDataLoadGenerator.generateAndSave(config);
        String counterId = counterGenerator.generateAndSave(config);
        String alarmId = alarmGenerator.generateAndSave(config);

        ARS ars = new ARS();
        ars.setNeType(config.getNeType());
        ars.setNeVersion(config.getNeVersion());
        ars.setUserStory(usId);
        ars.setObjectModel(omId);
        ars.setPmDataLoad(pmDlId);
        ars.setCounter(counterId);
        ars.setAlarm(alarmId);

        return arsRepository.save(ars);
    }

    private List<InterfaceObject> getInterfaces() {
        List<InterfaceObject> list = new ArrayList<>();
        for (String ifId : config.getInterfaces()) {
            Optional<InterfaceObject> ifaceOpt = interfaceRepository.findById(ifId);
            if (ifaceOpt.isPresent()) {
                list.add(ifaceOpt.get());
            } else {
                LOG.severe("There is no interface object with id: " + ifId);
            }
        }
        return list;
    }



    private List<AdaptationResource> getResources() {
        List<AdaptationResource> resources = new ArrayList<>();

        for (String srcId : config.getResources()) {
            Optional<AdaptationResource> opt = resourceRepository.findById(srcId);
            if (opt.isPresent()) {
                resources.add(opt.get());
            }
        }
        return resources;
    }
}
