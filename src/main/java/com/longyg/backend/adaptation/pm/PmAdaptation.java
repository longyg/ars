package com.longyg.backend.adaptation.pm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ylong on 2/14/2017.
 */
public class PmAdaptation {
    private String adapId;
    private String adapRelease;
    private String presentation;

    private List<ObjectClass> objectClasses = new ArrayList<ObjectClass>();
    private List<Measurement> measurements = new ArrayList<Measurement>();

    public void addObjectClass(ObjectClass objectClass) {
        if (!objectClasses.contains(objectClass)) {
            objectClasses.add(objectClass);
        }
    }

    public void addMeasurement(Measurement measurement) {
        if (!measurements.contains(measurement)) {
            measurements.add(measurement);
        }
    }

    public void buildTopology() {

    }

    public String getAdapId() {
        return adapId;
    }

    public void setAdapId(String adapId) {
        this.adapId = adapId;
    }

    public String getAdapRelease() {
        return adapRelease;
    }

    public void setAdapRelease(String adapRelease) {
        this.adapRelease = adapRelease;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public List<ObjectClass> getObjectClasses() {
        return objectClasses;
    }

    public void setObjectClasses(List<ObjectClass> objectClasses) {
        this.objectClasses = objectClasses;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    @Override
    public String toString() {
        return "Adaptation ID: " + adapId + ", Adaptation Release: " + adapRelease + ", Presentation: " + presentation;
    }
}
