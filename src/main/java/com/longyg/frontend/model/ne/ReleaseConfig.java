package com.longyg.frontend.model.ne;

import com.longyg.frontend.model.config.AlarmObject;
import com.longyg.frontend.model.config.ObjectLoad;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "release_configs")
public class ReleaseConfig {
    @Id
    private String id;

    private String neType;

    private String neVersion;

    private List<String> interfaces = new ArrayList<>();

    private List<String> adaptations = new ArrayList<>();

    private List<String> alarmObjects = new ArrayList<>();

    private List<String> objectLoads = new ArrayList<>();

    private List<ParentHierarchy> parentHierarchies = new ArrayList<>();

    private NeSize neSize;

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

    public List<String> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<String> interfaces) {
        this.interfaces = interfaces;
    }

    public List<String> getAdaptations() {
        return adaptations;
    }

    public void setAdaptations(List<String> adaptations) {
        this.adaptations = adaptations;
    }

    public List<String> getAlarmObjects() {
        return alarmObjects;
    }

    public void setAlarmObjects(List<String> alarmObjects) {
        this.alarmObjects = alarmObjects;
    }

    public List<String> getObjectLoads() {
        return objectLoads;
    }

    public void setObjectLoads(List<String> objectLoads) {
        this.objectLoads = objectLoads;
    }

    public List<ParentHierarchy> getParentHierarchies() {
        return parentHierarchies;
    }

    public void setParentHierarchies(List<ParentHierarchy> parentHierarchies) {
        this.parentHierarchies = parentHierarchies;
    }

    public NeSize getNeSize() {
        return neSize;
    }

    public void setNeSize(NeSize neSize) {
        this.neSize = neSize;
    }
}

class NeSize {
    private int maxNePerNetwork;
    private int avgNePerNetwork;

    public int getMaxNePerNetwork() {
        return maxNePerNetwork;
    }

    public void setMaxNePerNetwork(int maxNePerNetwork) {
        this.maxNePerNetwork = maxNePerNetwork;
    }

    public int getAvgNePerNetwork() {
        return avgNePerNetwork;
    }

    public void setAvgNePerNetwork(int avgNePerNetwork) {
        this.avgNePerNetwork = avgNePerNetwork;
    }
}

class ParentHierarchy {
    private String adaptationId;
    private String hierarchy;

    public String getAdaptationId() {
        return adaptationId;
    }

    public void setAdaptationId(String adaptationId) {
        this.adaptationId = adaptationId;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }
}