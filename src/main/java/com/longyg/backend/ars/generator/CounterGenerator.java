package com.longyg.backend.ars.generator;

import com.longyg.backend.adaptation.pm.Measurement;
import com.longyg.backend.adaptation.pm.PmDataLoadRepository;
import com.longyg.frontend.model.ars.ArsConfig;
import com.longyg.frontend.model.ars.counter.CounterMeas;
import com.longyg.frontend.model.ars.counter.CounterSpec;
import com.longyg.frontend.model.ars.pm.ArsMeasurement;
import com.longyg.frontend.model.ars.pm.MeasurementInfo;
import com.longyg.frontend.service.ArsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class CounterGenerator {
    @Autowired
    private ArsService arsService;

    private ArsConfig config;

    private PmDataLoadRepository pmDataLoadRepository;

    public String generateAndSave(ArsConfig config, PmDataLoadRepository pmDataLoadRepository) {
        this.config = config;
        this.pmDataLoadRepository = pmDataLoadRepository;

        CounterSpec spec = generateAndSave();
        return spec.getId();
    }

    private CounterSpec generateAndSave() {
        CounterSpec spec = new CounterSpec();
        spec.setNeType(config.getNeType());
        spec.setNeVersion(config.getNeVersion());

        for (Map.Entry<String, List<MeasurementInfo>> entry : pmDataLoadRepository.getAllReleaseMeasurements().entrySet()) {
            String adaptationId = entry.getKey();
            List<MeasurementInfo> miList = entry.getValue();

            addCounterMeas(spec, adaptationId, miList);
        }

        for (List<CounterMeas> measLit : spec.getMeasurementMap().values()) {
            Collections.sort(measLit);
        }

        spec = arsService.saveCounter(spec);

        return spec;
    }

    private void addCounterMeas(CounterSpec spec, String adaptationId, List<MeasurementInfo> miList) {
        for (MeasurementInfo mi : miList) {
            CounterMeas meas = createCounterMeas(mi);
            spec.addMeasurement(adaptationId, meas);
        }
    }

    private CounterMeas createCounterMeas(MeasurementInfo mi) {
        CounterMeas meas = new CounterMeas();
        meas.setName(mi.getName());



        return meas;
    }
}
