package com.longyg.backend.ars.generator;

import com.longyg.backend.TemplateRepository;
import com.longyg.backend.adaptation.main.AdaptationRepository;
import com.longyg.backend.adaptation.topology.PmbObject;
import com.longyg.backend.adaptation.topology.PmbObjectRepository;
import com.longyg.frontend.Utils.IntHolder;
import com.longyg.frontend.model.ars.ArsConfig;
import com.longyg.frontend.model.ars.om.ObjectClassInfo;
import com.longyg.frontend.model.ars.om.ObjectModelSpec;
import com.longyg.frontend.model.config.AdaptationResource;
import com.longyg.frontend.model.config.GlobalObject;
import com.longyg.frontend.service.ArsService;
import com.longyg.frontend.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Logger;

@Component
public class ObjectModelGenerator {
    private static final Logger LOG = Logger.getLogger(ObjectModelGenerator.class.getName());
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
        List<GlobalObject> globalObjects = configService.findGlobalObjects();
        pmbObjectRepository = new PmbObjectRepository(adaptationRepository, config, globalObjects);
        pmbObjectRepository.init();
    }

    private ObjectModelSpec generateAndSave() {
        ObjectModelSpec spec = new ObjectModelSpec();
        spec.setNeType(config.getNeType());
        spec.setNeVersion(config.getNeVersion());

        TreeSet<String> adaptationIds = findAdaptationIdsFromResource();

        List<PmbObject> primaryObjects = pmbObjectRepository.getAllReleaseObjects().get(adaptationIds.first());

        int i = 0;
        IntHolder row = new IntHolder(TemplateRepository.getObjectModelTplDef().getStartRow());
        for (String adaptationId : adaptationIds) {
            if (!pmbObjectRepository.getAllReleaseObjects().containsKey(adaptationId)) {
                continue;
            }
            List<PmbObject> rootObjects = getRootObjects(adaptationId);

            Collections.sort(rootObjects);

            if (i == 0) {
                for (PmbObject rootObject : rootObjects) {
                    IntHolder column = new IntHolder(0);
                    addPrimaryOci(spec, adaptationId, rootObject, row, column);
                    addPrimaryChildOci(spec, adaptationId, rootObject, row, column);
                    row.increase();
                }

            } else {
                for (PmbObject rootObject : rootObjects) {
                    IntHolder column = new IntHolder(0);
                    if (!primaryObjects.contains(rootObject)) {
                        addPrimaryOci(spec, adaptationId, rootObject, row, column);
                        addPrimaryChildOci(spec, adaptationId, rootObject, row, column);
                        row.increase();
                    } else {
                        checkForChildObjects(spec, adaptationId, rootObject, primaryObjects, row, column, true);
                    }
                }
            }
            i++;
        }

        spec = arsService.saveObjectModel(spec);
        return spec;
    }

    private void checkForChildObjects(ObjectModelSpec spec, String adaptationId, PmbObject parentObject, List<PmbObject> primaryObjects, IntHolder row, IntHolder column, boolean isParenTopExist) {
        List<PmbObject> childObjects = parentObject.getChildObjects();
        Collections.sort(childObjects);
        column.increase();
        for (PmbObject childObject : childObjects) {
            if (!primaryObjects.contains(childObject)) {
                row.increase();
                addPrimaryOci(spec, adaptationId, childObject, row, column);
                addPrimaryChildOci(spec, adaptationId, childObject, row, column);
            } else if (isParenTopExist) {
                checkForChildObjects(spec, adaptationId, childObject, primaryObjects, row, column, true);
            }
        }
    }

    private void addPrimaryChildOci(ObjectModelSpec spec, String adaptationId, PmbObject parentObject, IntHolder row, IntHolder column) {
        List<PmbObject> childObjects = parentObject.getChildObjects();
        if (null != childObjects && childObjects.size() > 0) {
            Collections.sort(childObjects);
            column.increase();
            for (PmbObject childObject : childObjects) {
                row.increase();
                addPrimaryOci(spec, adaptationId, childObject, row, column);
                addPrimaryChildOci(spec, adaptationId, childObject, row, column);
            }
            column.decrease();
        }
    }

    private void addPrimaryOci(ObjectModelSpec spec, String adaptationId, PmbObject pmbObject, IntHolder row, IntHolder column) {
        ObjectClassInfo oci = new ObjectClassInfo();
        oci.setRow(row.get());
        oci.setColumn(column.get());
        oci.setName(pmbObject.getName());
        oci.setAdaptationId(adaptationId);
        oci.setNameInOmes(pmbObject.getNameInOmes());
        oci.setMeasuredObject(pmbObject.isMeasuredObject());
        oci.setPresentation(pmbObject.getPresentation());
        oci.setTransient(pmbObject.isTransient());

        spec.addObjectClassInfo(adaptationId, oci);
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
