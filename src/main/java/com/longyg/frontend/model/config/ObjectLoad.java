package com.longyg.frontend.model.config;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ObjectLoad")
public class ObjectLoad {
    @Id
    private String id;
    private String objectClass;
    private int objectNumber;
    private String relatedObjectClass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(String objectClass) {
        this.objectClass = objectClass;
    }

    public int getObjectNumber() {
        return objectNumber;
    }

    public void setObjectNumber(int objectNumber) {
        this.objectNumber = objectNumber;
    }

    public String getRelatedObjectClass() {
        return relatedObjectClass;
    }

    public void setRelatedObjectClass(String relatedObjectClass) {
        this.relatedObjectClass = relatedObjectClass;
    }
}
