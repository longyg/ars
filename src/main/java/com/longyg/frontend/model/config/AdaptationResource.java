package com.longyg.frontend.model.config;

import com.longyg.frontend.model.ne.NetworkElement;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "adaptations")
public class AdaptationResource {
    @Id
    private String id;

    private Adaptation adaptation;

    private String svnUser;

    private String svnPassword;

    private String svnRoot;

    private String sourcePath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Adaptation getAdaptation() {
        return adaptation;
    }

    public void setAdaptation(Adaptation adaptation) {
        this.adaptation = adaptation;
    }

    public String getSvnUser() {
        return svnUser;
    }

    public void setSvnUser(String svnUser) {
        this.svnUser = svnUser;
    }

    public String getSvnPassword() {
        return svnPassword;
    }

    public void setSvnPassword(String svnPassword) {
        this.svnPassword = svnPassword;
    }

    public String getSvnRoot() {
        return svnRoot;
    }

    public void setSvnRoot(String svnRoot) {
        this.svnRoot = svnRoot;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }
}
