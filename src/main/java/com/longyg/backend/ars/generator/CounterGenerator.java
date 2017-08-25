package com.longyg.backend.ars.generator;

import com.longyg.frontend.model.ars.ArsConfig;
import org.springframework.stereotype.Component;

@Component
public class CounterGenerator {
    private ArsConfig config;

    public String generateAndSave(ArsConfig config) {
        this.config = config;

        return null;
    }
}
