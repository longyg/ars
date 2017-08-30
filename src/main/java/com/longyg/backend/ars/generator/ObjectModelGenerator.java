package com.longyg.backend.ars.generator;

import com.longyg.backend.adaptation.fm.FmRepository;
import com.longyg.backend.adaptation.main.AdaptationRepository;
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
    @Autowired
    private ResourceParser resourceParser;

    private ArsConfig config;

    private AdaptationRepository adaptationRepository;

    public String generateAndSave(ArsConfig config) throws Exception {
        this.config = config;

        List<String> resources = config.getResources();

        List<AdaptationResource> resourceList = new ArrayList<>();
        for (String srcId : resources) {
            AdaptationResource src = configService.findResource(srcId);
            downloadAndSave(src);

            resourceList.add(src);
        }

        adaptationRepository = new AdaptationRepository();
        resourceParser.parse(adaptationRepository, resourceList);


        ObjectModelSpec spec = generate();
        spec = arsService.saveObjectModel(spec);

        return spec.getId();
    }

    private ObjectModelSpec generate() {
        ObjectModelSpec spec = new ObjectModelSpec();



        return spec;
    }

    private void downloadAndSave(AdaptationResource src) throws Exception {
        // If the file already exists, no need to download
        if (src.getLocalPath() != null) {
            File localFile = new File(src.getLocalPath());
            if (localFile.exists())
                return;
        }

        SvnDownloader downloader = new SvnDownloader();
        downloader.download(src);

        // Save to DB for the local file path
        configService.saveResource(src);
    }
}
