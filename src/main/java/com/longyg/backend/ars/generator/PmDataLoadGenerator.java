package com.longyg.backend.ars.generator;

import com.longyg.backend.adaptation.main.AdaptationRepository;
import com.longyg.backend.adaptation.pm.Measurement;
import com.longyg.backend.adaptation.pm.PmAdaptation;
import com.longyg.backend.adaptation.pm.PmDataLoadRepository;
import com.longyg.frontend.model.ars.ArsConfig;
import com.longyg.frontend.model.ars.pm.MeasurementInfo;
import com.longyg.frontend.model.ars.pm.PmDataLoadSpec;
import com.longyg.frontend.service.ArsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PmDataLoadGenerator {
    @Autowired
    private ArsService arsService;

    private ArsConfig config;

    private AdaptationRepository adaptationRepository;

    private PmDataLoadRepository pmDataLoadRepository;

    public String generateAndSave(ArsConfig config, AdaptationRepository adaptationRepository) {
        this.config = config;
        this.adaptationRepository = adaptationRepository;

        initRepository();

        PmDataLoadSpec spec = generateAndSave();
        return spec.getId();
    }

    private PmDataLoadSpec generateAndSave() {
        PmDataLoadSpec spec = new PmDataLoadSpec();
        spec.setNeType(config.getNeType());
        spec.setNeVersion(config.getNeVersion());

        for (Map.Entry<String, List<MeasurementInfo>> entry : pmDataLoadRepository.getAllReleaseMeasurements().entrySet()) {
            String adaptationId = entry.getKey();
            List<MeasurementInfo> miList = entry.getValue();

            addArsMeasurements(spec, adaptationId, miList);
        }

        spec = arsService.savePmDataLoad(spec);

        return spec;
    }

    private void addArsMeasurements(PmDataLoadSpec spec, String adaptationId, List<MeasurementInfo> miList) {

    }

    private void initRepository() {
        pmDataLoadRepository = new PmDataLoadRepository(config, adaptationRepository);
        pmDataLoadRepository.init();
    }
}
