package com.longyg.frontend.model.ars;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection = "arsconfig")
public class ArsConfig {
    @Id
    private String id;
    private String neType;
    private String neVersion;
    private List<String> interfaces = new ArrayList<>();
    private Map<String, String> parents = new HashMap<>();
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

    public Map<String, String> getParents() {
        return parents;
    }

    public void setParents(Map<String, String> parents) {
        this.parents = parents;
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

    public boolean addResource(String srcId) {
        if (!resources.contains(srcId)) {
            resources.add(srcId);
            return true;
        }
        return false;
    }

    public boolean addInterface(String ifId) {
        if (!interfaces.contains(ifId)) {
            interfaces.add(ifId);
            return true;
        }
        return false;
    }

    public boolean addParent(String adaptationId, String parent) {
        String adapId = adaptationId.replaceAll("\\.", "_");
        if (!parents.containsKey(adapId)) {
            parents.put(adapId, parent);
            return true;
        }
        return false;
    }
}
