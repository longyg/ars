package com.longyg.backend.adaptation.pm;

import com.longyg.backend.adaptation.common.ManualCloseZipInputStream;
import com.longyg.backend.adaptation.common.Parser;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.zip.ZipEntry;

/**
 * Created by ylong on 2/17/2017.
 */
public class PmbZipParser implements Parser{
    private static final Logger LOG = Logger.getLogger(PmbZipParser.class);

    public void parse(InputStream is) {
        ManualCloseZipInputStream zin = new ManualCloseZipInputStream(is);
        ZipEntry entry = null;
        PmAdaptation pmAdaptation = new PmAdaptation();
        Parser parser;
        try {
            while ((entry = zin.getNextEntry()) != null)
            {
                String name = entry.getName();
                if (name.endsWith(".pmb"))
                {
                    LOG.debug("Parsing pmb: " + name);
                    parser = new PmbParser(pmAdaptation);
                    parser.parse(zin);
                }
                else if (name.endsWith(".pmb.common"))
                {
                    LOG.debug("Parsing pmb common: " + name);
                    parser = new PmbCommonParser(pmAdaptation);
                    parser.parse(zin);
                }
            }
        } catch (Exception e) {
            LOG.error("Exception while parsing PMB zip: " + entry.getName(), e);
        }
        LOG.debug("Adding pmAdaptation: " + pmAdaptation);
        PmRepository.addPmAdaptation(pmAdaptation.getAdapRelease(), pmAdaptation);
    }

}
