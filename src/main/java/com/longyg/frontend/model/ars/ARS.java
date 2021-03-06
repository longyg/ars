package com.longyg.frontend.model.ars;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "ars")
public class ARS {
    @Id
    private String id;
    private String neType;
    private String neVersion;
    private String lastNeVersion;
    private List<String> olderNeVersions = new ArrayList<>();
    private String userStory;
    private String objectModel;
    private String pmDataLoad;
    private String counter;
    private String alarm;

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

    public String getUserStory() {
        return userStory;
    }

    public void setUserStory(String userStory) {
        this.userStory = userStory;
    }

    public String getObjectModel() {
        return objectModel;
    }

    public void setObjectModel(String objectModel) {
        this.objectModel = objectModel;
    }

    public String getPmDataLoad() {
        return pmDataLoad;
    }

    public void setPmDataLoad(String pmDataLoad) {
        this.pmDataLoad = pmDataLoad;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public String getLastNeVersion() {
        return lastNeVersion;
    }

    public void setLastNeVersion(String lastNeVersion) {
        this.lastNeVersion = lastNeVersion;
    }

    public List<String> getOlderNeVersions() {
        return olderNeVersions;
    }

    public void setOlderNeVersions(List<String> olderNeVersions) {
        this.olderNeVersions = olderNeVersions;
    }
}
