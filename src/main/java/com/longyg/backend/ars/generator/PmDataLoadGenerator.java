package com.longyg.backend.ars.generator;

import com.longyg.backend.adaptation.main.AdaptationRepository;
import com.longyg.backend.adaptation.pm.Measurement;
import com.longyg.backend.adaptation.pm.PmAdaptation;
import com.longyg.backend.adaptation.pm.PmDataLoadRepository;
import com.longyg.backend.adaptation.topology.PmbObject;
import com.longyg.frontend.model.ars.ARS;
import com.longyg.frontend.model.ars.ArsConfig;
import com.longyg.frontend.model.ars.om.ObjectClassInfo;
import com.longyg.frontend.model.ars.om.ObjectModelSpec;
import com.longyg.frontend.model.ars.pm.ArsMeasurement;
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

    private ObjectModelSpec om;

    public String generateAndSave(ArsConfig config, AdaptationRepository adaptationRepository, ObjectModelSpec om) throws Exception {
        this.config = config;
        this.adaptationRepository = adaptationRepository;
        this.om = om;

        initRepository();

        PmDataLoadSpec spec = generateAndSave();
        return spec.getId();
    }

    private PmDataLoadSpec generateAndSave() throws Exception {
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

    private void addArsMeasurements(PmDataLoadSpec spec, String adaptationId, List<MeasurementInfo> miList) throws Exception{
        for (MeasurementInfo mi : miList) {
            ArsMeasurement meas = createArsMeasurement(mi);
            spec.addMeasurement(adaptationId, meas);
        }
    }

    private ArsMeasurement createArsMeasurement(MeasurementInfo mi) throws Exception {
        ArsMeasurement meas = new ArsMeasurement();
        meas.setName(mi.getName());
        meas.setNameInOmes(mi.getNameInOmes());
        meas.setMeasuredObject(mi.getMeasuredObject());
        meas.setSupported(mi.getSupportedVersions().contains(config.getNeVersion()));
        meas.setSupportedPreviousVersions(getOtherVersions(mi));
        meas.setDimensions(mi.getDimensions());

        ObjectClassInfo oci = getMeasuredOci(mi);
        if (null == oci) {
            throw new Exception("Can't find ObjectClassInfo for " + mi.getName());
        }
        meas.setAvgPerNet(oci.getAvgPerNet());
        meas.setMaxPerNet(oci.getMaxPerNet());
        meas.setMaxPerNe(oci.getMaxPerNE());
        meas.setCounterNumber(getCnOfVersion(mi, config.getNeVersion()));
        meas.setCounterNumberOfLastVersion(getCnOfVersion(mi, config.getLastVersion()));

        return meas;
    }

    private int getCnOfVersion(MeasurementInfo mi, String version) {
        return mi.getAllReleaseCounters().get(version).size();
    }

    private ObjectClassInfo getMeasuredOci(MeasurementInfo mi) {
        List<ObjectClassInfo> ociList = om.getOciMap().get(mi.getAdaptationId());
        String hierarchy = mi.getMeasuredObject();
        for (ObjectClassInfo oci : ociList) {
            if (oci.getOriginalDn().equals(hierarchy)) {
                return oci;
            }
        }
        return null;
    }

    private List<String> getOtherVersions(MeasurementInfo mi) {
        List<String> otherVerions = new ArrayList<>();
        for (String version : mi.getSupportedVersions()) {
            if (!version.equals(config.getNeVersion())) {
                otherVerions.add(version);
            }
        }
        return otherVerions;
    }

    private void initRepository() {
        pmDataLoadRepository = new PmDataLoadRepository(config, adaptationRepository);
        pmDataLoadRepository.init();
    }
}
