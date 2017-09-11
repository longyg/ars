package com.longyg.frontend.model.config;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "load")
public class LoadConfig {
    @Id
    private String id;
    private String name;
    private String neType;
    private String adaptationId;
    private List<ObjectLoad> objectLoads = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeType() {
        return neType;
    }

    public void setNeType(String neType) {
        this.neType = neType;
    }

    public String getAdaptationId() {
        return adaptationId;
    }

    public void setAdaptationId(String adaptationId) {
        this.adaptationId = adaptationId;
    }

    public List<ObjectLoad> getObjectLoads() {
        return objectLoads;
    }

    public void setObjectLoads(List<ObjectLoad> objectLoads) {
        this.objectLoads = objectLoads;
    }
}
