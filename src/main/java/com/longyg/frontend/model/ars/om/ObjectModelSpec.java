package com.longyg.frontend.model.ars.om;

import com.longyg.backend.adaptation.pm.ObjectClass;
import com.longyg.backend.adaptation.pm.PmAdaptation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collection = "om")
public class ObjectModelSpec {
    @Id
    private String id;

    private String neType;

    private String neVersion;

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

    public void addObjectClassInfo(String adaptationId, ObjectClassInfo oci) {
        if (!ociMap.containsKey(adaptationId)) {
            TreeSet<ObjectClassInfo> ociSet = ociMap.get(adaptationId);
            if (!ociSet.contains(oci)) {
                ociSet.add(oci);
            }
        } else {
            TreeSet<ObjectClassInfo> ociSet = new TreeSet<>();
            ociSet.add(oci);
            ociMap.put(adaptationId, ociSet);
        }
    }
}
