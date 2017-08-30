package com.longyg.backend.ars.generator;

import com.longyg.backend.adaptation.fm.FmRepository;
import com.longyg.backend.adaptation.main.AdaptationRepository;
import com.longyg.backend.adaptation.main.AdaptationResourceParser;
import com.longyg.backend.adaptation.main.ResourceParser;
import com.longyg.backend.adaptation.pm.PmRepository;
import com.longyg.backend.adaptation.svn.SvnDownloader;
import com.longyg.frontend.model.ars.ArsConfig;
import com.longyg.frontend.model.ars.om.ObjectModelSpec;
import com.longyg.frontend.model.config.AdaptationResource;
import com.longyg.frontend.service.ArsService;
import com.longyg.frontend.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class ObjectModelGenerator {
    @Autowired
    private ArsService arsService;
    @Autowired
    private ConfigService configService;

    private ArsConfig config;

    private AdaptationRepository adaptationRepository;

    public String generateAndSave(ArsConfig config, AdaptationRepository adaptationRepository) throws Exception {
        this.config = config;
        this.adaptationRepository = adaptationRepository;

        ObjectModelSpec spec = generate();
        spec = arsService.saveObjectModel(spec);

        return spec.getId();
    }

    private ObjectModelSpec generate() {
        ObjectModelSpec spec = new ObjectModelSpec();



        return spec;
    }


}
