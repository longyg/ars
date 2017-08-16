package com.longyg.frontend.model.object;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "objects")
public class GlobalObject {
    @Id
    private String id;
    private String name;
    private String presentation;
    private String nameInOMeS;
    private boolean isTransient;

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

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getNameInOMeS() {
        return nameInOMeS;
    }

    public void setNameInOMeS(String nameInOMeS) {
        this.nameInOMeS = nameInOMeS;
    }

    public boolean isTransient() {
        return isTransient;
    }

    public void setTransient(boolean aTransient) {
        isTransient = aTransient;
    }
}
