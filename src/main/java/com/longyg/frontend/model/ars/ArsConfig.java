package com.longyg.frontend.model.ars;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "arsconfig")
public class ArsConfig {
    @Id
    private String id;
    private String neType;
    private String neVersion;
    private List<String> interfaces = new ArrayList<>();
    private String parent;
    private String neParamId;
    private List<String> resources = new ArrayList<>();

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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getNeParamId() {
        return neParamId;
    }

    public void setNeParamId(String neParamId) {
        this.neParamId = neParamId;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }
}
