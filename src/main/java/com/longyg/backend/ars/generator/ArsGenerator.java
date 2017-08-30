package com.longyg.backend.ars.generator;

import com.longyg.backend.adaptation.main.AdaptationRepository;
import com.longyg.backend.adaptation.main.AdaptationResourceParser;
import com.longyg.backend.adaptation.svn.SvnDownloader;
import com.longyg.frontend.model.ars.ARS;
import com.longyg.frontend.model.ars.ArsConfig;
import com.longyg.frontend.model.ars.ArsRepository;
import com.longyg.frontend.model.config.AdaptationResource;
import com.longyg.frontend.model.config.AdaptationResourceRepository;
import com.longyg.frontend.model.config.InterfaceObject;
import com.longyg.frontend.model.config.InterfaceRepository;
import com.longyg.frontend.service.ArsService;
import com.longyg.frontend.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class ArsGenerator {
    private static final Logger LOG = Logger.getLogger(ArsGenerator.class.getName());

    private ArsConfig config;

    @Autowired
    private ArsService arsService;

    @Autowired
    private ConfigService configService;

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

    @Autowired
    private AdaptationResourceParser resourceParser;

    private AdaptationRepository adaptationRepository;

    public ARS generateAndSave(ArsConfig config) throws Exception {
        this.config = config;

        initAdaptationRepository();

        String usId = usGenerator.generateAndSave(config);
        String omId = omGenerator.generateAndSave(config, adaptationRepository);
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

        return arsService.saveArs(ars);
    }

    private void initAdaptationRepository() throws Exception {
        List<String> resources = config.getResources();

        List<AdaptationResource> resourceList = new ArrayList<>();
        for (String srcId : resources) {
            AdaptationResource src = configService.findResource(srcId);
            downloadAndSave(src);

            resourceList.add(src);
        }
        adaptationRepository = new AdaptationRepository();
        resourceParser.parse(adaptationRepository, resourceList);
    }

    private List<InterfaceObject> getInterfaces() {
        List<InterfaceObject> list = new ArrayList<>();
        for (String ifId : config.getInterfaces()) {
            InterfaceObject ifo = configService.findInterface(ifId);
            if (null != ifo) {
                list.add(ifo);
            } else {
                LOG.severe("There is no interface object with id: " + ifId);
            }
        }
        return list;
    }

    private List<AdaptationResource> getResources() {
        List<AdaptationResource> resources = new ArrayList<>();

        for (String srcId : config.getResources()) {
            AdaptationResource resource = configService.findResource(srcId);
            if (null != resource) {
                resources.add(resource);
            }
        }
        return resources;
    }

    private void downloadAndSave(AdaptationResource src) throws Exception {
        // If the file already exists, no need to download
        if (src.getLocalPath() != null) {
            File localFile = new File(src.getLocalPath());
            if (localFile.exists())
                return;
        }

        SvnDownloader downloader = new SvnDownloader();
        downloader.download(src);

        // Save to DB for the local file path
        configService.saveResource(src);
    }
}
