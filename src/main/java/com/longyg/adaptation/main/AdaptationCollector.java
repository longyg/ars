package com.longyg.adaptation.main;

import com.longyg.adaptation.config.ConfigReader;
import com.longyg.adaptation.config.ConfigRepository;
import com.longyg.adaptation.fm.AlarmGenerator;
import com.longyg.adaptation.pm.CounterGenerator;
import com.longyg.adaptation.pm.MeasurementGenerator;
import com.longyg.adaptation.pm.OmesGenerator;
import com.longyg.adaptation.topology.TopologyGenerator;

import java.io.File;

/**
 * Created by ylong on 2/9/2017.
 */
public class AdaptationCollector {
    private ConfigReader configReader;
    private String configFile;
    private ConfigRepository configRepository;

    public AdaptationCollector(String configFile) {
        this.configFile = configFile;
        this.configRepository = ConfigRepository.getInstance();
    }

    private void initialize() throws Exception {
        readConfig();
        ResourceRepository.initialize();
        ResourceParser parser = new ResourceParser();
        parser.parse();
    }

    private void generateResult() throws Exception {
        TopologyGenerator topologyGenerator = new TopologyGenerator();
        topologyGenerator.generate();

        MeasurementGenerator measurementGenerator = new MeasurementGenerator();
        measurementGenerator.generate();

        CounterGenerator counterGenerator = new CounterGenerator();
        counterGenerator.generate();

        AlarmGenerator alarmGenerator = new AlarmGenerator();
        alarmGenerator.generate();

        OmesGenerator omesGenerator = new OmesGenerator();
        omesGenerator.generateOMeS();
    }

    private void readConfig() throws Exception {
        configReader = new ConfigReader();
        configReader.readConfig(new File(configFile), configRepository);
    }

    public static void main(String[] args) throws Exception {
        String configFile = args[0];

        AdaptationCollector collector = new AdaptationCollector(configFile);
        collector.initialize();
        collector.generateResult();
    }
}
