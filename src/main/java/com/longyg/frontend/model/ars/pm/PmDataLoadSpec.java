package com.longyg.frontend.model.ars.pm;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection = "pm")
public class PmDataLoadSpec {
    private String id;
    private String neType;
    private String neVersion;

    private Map<String, List<ArsMeasurement>> measurementMap = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNeType() {
        return neType;
    }

    public void setNeType(String neType) {
        this.neType = neType;
    }

    public String getNeVersion() {
        return neVersion;
    }

    public void setNeVersion(String neVersion) {
        this.neVersion = neVersion;
    }

    public Map<String, List<ArsMeasurement>> getMeasurementMap() {
        return measurementMap;
    }

    public void setMeasurementMap(Map<String, List<ArsMeasurement>> measurementMap) {
        this.measurementMap = measurementMap;
    }
}
