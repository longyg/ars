package com.longyg.frontend.model.ars.pm;

import com.longyg.backend.adaptation.pm.Counter;
import com.longyg.backend.adaptation.pm.Measurement;
import com.longyg.backend.adaptation.topology.PmbObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeasurementInfo {
    private int row;
    private Measurement measurement;
    private String adaptationId;
    private String name;
    private String nameInOmes;
    private PmbObject measuredObject;
    private boolean isSupported;
    private List<String> supportedVersions = new ArrayList<>();
    private List<String> dimensions = new ArrayList<>();

    private Map<String, List<Counter>> allReleaseCounters = new HashMap<>();

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public String getAdaptationId() {
        return adaptationId;
    }

    public void setAdaptationId(String adaptationId) {
        this.adaptationId = adaptationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameInOmes() {
        return nameInOmes;
    }

    public void setNameInOmes(String nameInOmes) {
        this.nameInOmes = nameInOmes;
    }

    public PmbObject getMeasuredObject() {
        return measuredObject;
    }

    public void setMeasuredObject(PmbObject measuredObject) {
        this.measuredObject = measuredObject;
    }

    public boolean isSupported() {
        return isSupported;
    }

    public void setSupported(boolean supported) {
        isSupported = supported;
    }

    public List<String> getSupportedVersions() {
        return supportedVersions;
    }

    public void setSupportedVersions(List<String> supportedVersions) {
        this.supportedVersions = supportedVersions;
    }

    public List<String> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<String> dimensions) {
        this.dimensions = dimensions;
    }

    public void addSupportedVersion(String version) {
        if (!supportedVersions.contains(version)) {
            supportedVersions.add(version);
        }
    }

    public void addCounters(String version, List<Counter> counters) {
        if (!allReleaseCounters.containsKey(version)) {
            allReleaseCounters.put(version, counters);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeasurementInfo that = (MeasurementInfo) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
