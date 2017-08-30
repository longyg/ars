package com.longyg.frontend.model.ars.om;

import java.util.Set;
import java.util.TreeSet;

public class AdaptationObjectClassInfo {
    private String neType;
    private String neVersion;
    private String adaptationId;
    private Set<ObjectClassInfo> ociList = new TreeSet<>();
}
