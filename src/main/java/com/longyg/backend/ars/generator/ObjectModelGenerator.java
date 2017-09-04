package com.longyg.backend.ars.generator;

import com.longyg.backend.adaptation.fm.FmRepository;
import com.longyg.backend.adaptation.main.AdaptationRepository;
import com.longyg.backend.adaptation.main.AdaptationResourceParser;
import com.longyg.backend.adaptation.main.ResourceParser;
import com.longyg.backend.adaptation.pm.PmRepository;
import com.longyg.backend.adaptation.svn.SvnDownloader;
import com.longyg.backend.adaptation.topology.PmbObject;
import com.longyg.backend.adaptation.topology.PmbObjectRepository;
import com.longyg.frontend.model.ars.ArsConfig;
import com.longyg.frontend.model.ars.om.ObjectModelSpec;
import com.longyg.frontend.model.config.AdaptationResource;
import com.longyg.frontend.service.ArsService;
import com.longyg.frontend.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Component
public class ObjectModelGenerator {
    @Autowired
    private ArsService arsService;
    @Autowired
    private ConfigService configService;

    private ArsConfig config;

    private AdaptationRepository adaptationRepository;

    private PmbObjectRepository pmbObjectRepository;

    public String generateAndSave(ArsConfig config, AdaptationRepository adaptationRepository) throws Exception {
        this.config = config;
        this.adaptationRepository = adaptationRepository;

        initRepository();

        ObjectModelSpec spec = generateAndSave();
        return spec.getId();
    }

    private void initRepository() throws Exception {
        pmbObjectRepository = new PmbObjectRepository(adaptationRepository, config);
        pmbObjectRepository.init();
    }

    private ObjectModelSpec generateAndSave() {
        ObjectModelSpec spec = new ObjectModelSpec();

        TreeSet<String> adaptationIds = findAdaptationIdsFromResource();

        adaptationIds.stream().forEach(adaptationId -> {
            if (pmbObjectRepository.getAllReleaseObjects().containsKey(adaptationId)) {

                List<PmbObject> rootObjects = getRootObjects(adaptationId);

                rootObjects.stream().forEach(rootObject -> {

                });

            }
        });

        spec = arsService.saveObjectModel(spec);
        return spec;
    }

    private List<PmbObject> getRootObjects(String adaptationId) {
        List<PmbObject> rootObjects = new ArrayList<>();
        List<PmbObject> pmbObjects = pmbObjectRepository.getAllReleaseObjects().get(adaptationId);
        pmbObjects.stream().forEach(pmbObject -> {
            if (pmbObject.getParentObjects().size() < 1) {
                rootObjects.add(pmbObject);
            }
        });
        return rootObjects;
    }

    private TreeSet<String> findAdaptationIdsFromResource() {
        TreeSet<String> adaptations = new TreeSet<>();
        List<String> resources = config.getResources();
        List<AdaptationResource> adapSrcs = new ArrayList<>();
        resources.stream().forEach(srcId -> {
            AdaptationResource resource = configService.findResource(srcId);
            if (null != resource) {
                String adaptationId = resource.getAdaptation().getId();
                if (!adaptations.contains(adaptationId)) {
                    adaptations.add(adaptationId);
                }
            }
        });
        return adaptations;
    }
}
