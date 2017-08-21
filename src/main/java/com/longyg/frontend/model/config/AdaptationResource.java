package com.longyg.frontend.model.config;

import com.longyg.frontend.model.ne.NetworkElement;

import java.util.ArrayList;
import java.util.List;

public class AdaptationResource {
    private NetworkElement ne;
    private SvnConfig svnConfig;
    private List<String> svnResources = new ArrayList<>();

    public NetworkElement getNe() {
        return ne;
    }

    public void setNe(NetworkElement ne) {
        this.ne = ne;
    }

    public SvnConfig getSvnConfig() {
        return svnConfig;
    }

    public void setSvnConfig(SvnConfig svnConfig) {
        this.svnConfig = svnConfig;
    }

    public List<String> getSvnResources() {
        return svnResources;
    }

    public void setSvnResources(List<String> svnResources) {
        this.svnResources = svnResources;
    }
}
