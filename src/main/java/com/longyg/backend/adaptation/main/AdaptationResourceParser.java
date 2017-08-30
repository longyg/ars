package com.longyg.backend.adaptation.main;

import com.longyg.backend.adaptation.common.ManualCloseZipInputStream;
import com.longyg.backend.adaptation.common.Parser;
import com.longyg.backend.adaptation.fm.FmAdaptation;
import com.longyg.backend.adaptation.fm.FmPackageParser;
import com.longyg.backend.adaptation.fm.ManZipParser;
import com.longyg.backend.adaptation.pm.PmAdaptation;
import com.longyg.backend.adaptation.pm.PmbPackageParser;
import com.longyg.backend.adaptation.pm.PmbZipParser;
import com.longyg.frontend.model.config.AdaptationResource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;

/**
 * Created by ylong on 2/14/2017.
 */
@Component
public class AdaptationResourceParser {
    private static final Logger LOG = Logger.getLogger(AdaptationResourceParser.class);

    private AdaptationRepository adaptationRepository;

    public void parse(AdaptationRepository adaptationRepository, List<AdaptationResource> resourceList) throws Exception {
        this.adaptationRepository = adaptationRepository;

        for (AdaptationResource resource : resourceList) {
            parseOneResource(resource);
        }
    }

    private void parseOneResource(AdaptationResource resource) throws Exception {
        LOG.debug("Parsing resource: " + resource.getLocalPath());
        InputStream inputstream = new FileInputStream(resource.getLocalPath());
        ManualCloseZipInputStream zin = new ManualCloseZipInputStream(inputstream);
        ZipEntry entry = null;
        try {
            while ((entry = zin.getNextEntry()) != null)
            {
                String name = entry.getName();

                LOG.debug("Parsing zip entry: " + name);
                if (name.contains(".pmb"))
                {
                    LOG.debug("Parsing pmb zip: " + name);
                    PmbPackageParser parser = new PmbPackageParser();
                    PmAdaptation pmAdaptation = parser.parse(zin);
                    adaptationRepository.addPmAdaptation(pmAdaptation.getAdapRelease(), pmAdaptation);
                }
                else if (name.contains(".man"))
                {
                    LOG.debug("Parsing man zip: " + name);
                    FmPackageParser parser = new FmPackageParser();
                    FmAdaptation fmAdaptation = parser.parse(zin);
                    adaptationRepository.addFmAdaptation(fmAdaptation.getAdapRelease(), fmAdaptation);
                }
            }
        } catch (IOException e) {
            LOG.error("Exception while parsing resource: " + resource.getLocalPath(), e);
            throw new Exception("Exception while parsing resource: " + resource.getLocalPath(), e);
        } finally {
            try {
                zin.doClose();
            } catch (IOException e) {
                LOG.error("Exception while closing resource stream", e);
            }
        }
    }
}
