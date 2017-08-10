package com.longyg.frontend.model.ne;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nes")
public class NetworkElement {
    @Id
    private String id;
    private String neType;
    private String neVersion;
    private String remarks;

    public NetworkElement(String neType, String neVersion, String remarks) {
        this.neType = neType;
        this.neVersion = neVersion;
        this.remarks = remarks;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
