package com.longyg.frontend.model.config;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "LoadConfig")
public class LoadConfig {
    @Id
    private String id;
    private String name;
    private String neTypeId;
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

    public String getNeTypeId() {
        return neTypeId;
    }

    public void setNeTypeId(String neTypeId) {
        this.neTypeId = neTypeId;
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