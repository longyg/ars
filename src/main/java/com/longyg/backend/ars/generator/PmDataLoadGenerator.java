package com.longyg.backend.ars.generator;

import com.longyg.backend.adaptation.main.AdaptationRepository;
import com.longyg.backend.adaptation.pm.Measurement;
import com.longyg.backend.adaptation.pm.PmAdaptation;
import com.longyg.frontend.model.ars.ArsConfig;
import com.longyg.frontend.model.ars.pm.MeasurementInfo;
import com.longyg.frontend.model.ars.pm.PmDataLoadSpec;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PmDataLoadGenerator {
    private ArsConfig config;
    private AdaptationRepository adaptationRepository;

    public String generateAndSave(ArsConfig config, AdaptationRepository adaptationRepository) {
        this.config = config;
        this.adaptationRepository = adaptationRepository;

        PmDataLoadSpec spec = generateAndSave();
        return spec.getId();
    }

    private PmDataLoadSpec generateAndSave() {
        PmDataLoadSpec spec = new PmDataLoadSpec();
        spec.setNeType(config.getNeType());
        spec.setNeVersion(config.getNeVersion());

        Map<String, List<PmAdaptation>> pmAdaptationsMap = adaptationRepository.getPmAdaptations();

        for (Map.Entry<String, List<PmAdaptation>> entry : pmAdaptationsMap.entrySet()) {
            String adaptationId = entry.getKey();
            List<PmAdaptation> pmAdaptations = entry.getValue();

            for (PmAdaptation pmAdaptation : pmAdaptations) {
                for (Measurement measurement : pmAdaptation.getMeasurements()) {

                    MeasurementInfo mi = new MeasurementInfo();
                    mi.setMeasurement(measurement);
                    mi.setName(measurement.getName());
                    mi.setNameInOmes(mi.getNameInOmes());

                }
            }

        }

        return spec;
    }
}
