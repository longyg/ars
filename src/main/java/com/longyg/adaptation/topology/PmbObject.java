package com.longyg.adaptation.topology;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ylong on 2/15/2017.
 */
public class PmbObject implements Comparable<PmbObject> {
    private String name;
    private String nameInOmes;
    private boolean isTransient;
    private String presentation;
    private boolean isMeasuredObject;

    private List<String> supporteredVersions = new ArrayList<String>();
    private List<String> dimensions = new ArrayList<String>();

    private List<PmbObject> childObjects = new ArrayList<PmbObject>();
    private List<PmbObject> parentObjects = new ArrayList<PmbObject>();

    public void addSupportedVersion(String version) {
        if (!supporteredVersions.contains(version)) {
            supporteredVersions.add(version);
        }
    }

    public void addDimension(String dimension) {
        if (!dimensions.contains(dimension)) {
            dimensions.add(dimension);
        }
    }

    public void addParentObject(PmbObject parent) {
        if (!parentObjects.contains(parent)) {
            parentObjects.add(parent);
        }
    }

    public void addChildObject(PmbObject child) {
        if (!childObjects.contains(child)) {
            childObjects.add(child);
        }
    }

    @Override
    public String toString() {
        return "PmbObject{" +
                "name='" + name + '\'' +
                ", supporteredVersions=" + supporteredVersions +
                ", childObjects=" + childObjects +
                '}';
    }

    public boolean isMeasuredObject() {
        return isMeasuredObject;
    }

    public void setMeasuredObject(boolean measuredObject) {
        isMeasuredObject = measuredObject;
    }

    public List<String> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<String> dimensions) {
        this.dimensions = dimensions;
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

    public boolean isTransient() {
        return isTransient;
    }

    public void setTransient(boolean aTransient) {
        isTransient = aTransient;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public List<String> getSupportedVersions() {
        return supporteredVersions;
    }

    public void setSupporteredVersions(List<String> supporteredVersions) {
        this.supporteredVersions = supporteredVersions;
    }

    public List<PmbObject> getChildObjects() {
        return childObjects;
    }

    public void setChildObjects(List<PmbObject> childObjects) {
        this.childObjects = childObjects;
    }

    public List<PmbObject> getParentObjects() {
        return parentObjects;
    }

    public void setParentObjects(List<PmbObject> parentObjects) {
        this.parentObjects = parentObjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PmbObject pmbObject = (PmbObject) o;

        return name.equals(pmbObject.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public int compareTo(PmbObject o) {
        return this.getNameInOmes().compareTo(o.getName());
    }
}
