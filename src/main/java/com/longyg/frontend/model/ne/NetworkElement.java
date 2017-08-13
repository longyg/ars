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

    public NetworkElement() {
    }

    public NetworkElement(String neType, String neVersion) {
        this.neType = neType;
        this.neVersion = neVersion;
    }

    public NetworkElement(String neType, String neVersion, String remarks) {
        this.neType = neType;
        this.neVersion = neVersion;
        this.remarks = remarks;
    }

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetworkElement that = (NetworkElement) o;

        if (neType != null ? !neType.equals(that.neType) : that.neType != null) return false;
        return neVersion != null ? neVersion.equals(that.neVersion) : that.neVersion == null;
    }

    @Override
    public int hashCode() {
        int result = neType != null ? neType.hashCode() : 0;
        result = 31 * result + (neVersion != null ? neVersion.hashCode() : 0);
        return result;
    }
}
