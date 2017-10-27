package com.longyg.frontend.model.ars.pm;

import com.longyg.backend.adaptation.pm.Measurement;
import com.longyg.backend.adaptation.topology.PmbObject;
import com.longyg.frontend.model.ars.om.ObjectClassInfo;

import java.util.ArrayList;
import java.util.List;

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
}
