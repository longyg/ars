package com.longyg.frontend.model.config;

import com.longyg.frontend.model.iface.InterfaceObject;
import com.longyg.frontend.model.ne.NeRelease;

import java.util.ArrayList;
import java.util.List;

public class NeConfig {
    private String neType;
    private String neVersion;
    private List<String> interfaces = new ArrayList<>();
    private String parent;
    private String neParamName;
}
