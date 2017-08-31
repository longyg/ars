package com.longyg.backend.adaptation.topology;

import com.longyg.backend.adaptation.main.AdaptationRepository;
import com.longyg.backend.adaptation.pm.*;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PmbObjectRepository {
    private static final Logger LOG = Logger.getLogger(PmbObjectRepository.class);

    // key: adaptation Id
    // value: all releases' object of specific adaptation id
    private Map<String, List<PmbObject>> allReleaseObjects = new HashMap<>();

    private AdaptationRepository adaptationRepository;

    public PmbObjectRepository(AdaptationRepository adaptationRepository) {
        this.adaptationRepository = adaptationRepository;
    }

    public void init() throws Exception {

    }

    private void createPmbObjectFromPmb() throws Exception {
        Map<String, PmAdaptation> pmAdaptations = adaptationRepository.getPmAdaptations();

        for (Map.Entry<String, PmAdaptation> entry : pmAdaptations.entrySet()) {
            String version = entry.getKey();
            PmAdaptation pmAdaptation = entry.getValue();
            LOG.debug("version:" + version + ", pmAdaptation: " + pmAdaptation);

            List<ObjectClass> pmClasses = pmAdaptation.getObjectClasses();
            for (Measurement measurement : pmAdaptation.getMeasurements()) {
                for (MeasuredTarget measuredTarget : measurement.getMeasuredTargets()) {
                    String dimension = measuredTarget.getDimension();
                    for (String hierarchy : measuredTarget.getHierarchies()) {
                        hierarchy = format(hierarchy);
                        createPmObjectsFromHierarchy(hierarchy, null, version, dimension, pmClasses);
                    }
                }
            }
        }
    }
}
