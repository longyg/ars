package com.longyg.frontend.model.config;

public class ParentObject {
    private GlobalObject object;
    private ParentObject parentObject;

    public GlobalObject getObject() {
        return object;
    }

    public void setObject(GlobalObject object) {
        this.object = object;
    }

    public ParentObject getParentObject() {
        return parentObject;
    }

    public void setParentObject(ParentObject parentObject) {
        this.parentObject = parentObject;
    }
}
