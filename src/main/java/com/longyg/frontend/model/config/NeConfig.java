package com.longyg.frontend.model.config;

import com.longyg.frontend.model.iface.InterfaceObject;
import com.longyg.frontend.model.ne.NetworkElement;

import java.util.ArrayList;
import java.util.List;

public class NeConfig {
    private NetworkElement ne;

    private List<InterfaceObject> interfaceObjects = new ArrayList<>();
    private ParentObject parentObject;

    public NetworkElement getNe() {
        return ne;
    }

    public void setNe(NetworkElement ne) {
        this.ne = ne;
    }

    public List<InterfaceObject> getInterfaceObjects() {
        return interfaceObjects;
    }

    public void setInterfaceObjects(List<InterfaceObject> interfaceObjects) {
        this.interfaceObjects = interfaceObjects;
    }

    public ParentObject getParentObject() {
        return parentObject;
    }

    public void setParentObject(ParentObject parentObject) {
        this.parentObject = parentObject;
    }
}
