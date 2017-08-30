package com.longyg.frontend.model.ars.om;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Document(collection = "om")
public class ObjectModelSpec {
    @Id
    private String id;

    private Map<String, TreeSet<ObjectClassInfo>> ociMap = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, TreeSet<ObjectClassInfo>> getOciMap() {
        return ociMap;
    }

    public void setOciMap(Map<String, TreeSet<ObjectClassInfo>> ociMap) {
        this.ociMap = ociMap;
    }
}
