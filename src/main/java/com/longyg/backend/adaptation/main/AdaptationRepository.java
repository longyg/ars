package com.longyg.backend.adaptation.main;

import com.longyg.backend.adaptation.fm.FmAdaptation;
import com.longyg.backend.adaptation.pm.PmAdaptation;

import java.util.HashMap;
import java.util.Map;

public class AdaptationRepository {
    private Map<String, PmAdaptation> pmAdaptations = new HashMap<>();
    private Map<String, FmAdaptation> fmAdaptations = new HashMap<>();

    public Map<String, PmAdaptation> getPmAdaptations() {
        return pmAdaptations;
    }

    public void addPmAdaptation(String version, PmAdaptation pmAdaptation) {
        if (!pmAdaptations.containsKey(version)) {
            pmAdaptations.put(version, pmAdaptation);
        }
    }

    public Map<String, FmAdaptation> getFmAdaptations() {
        return fmAdaptations;
    }

    public void addFmAdaptation(String version, FmAdaptation fmAdaptation) {
        if (!fmAdaptations.containsKey(version)) {
            fmAdaptations.put(version, fmAdaptation);
        }
    }
}
