package com.carboat.scamdetector;

import com.carboat.scamdetector.di.DaggerScamDetectorApplication;
import com.carboat.scamdetector.di.ScamDetectorApplication;
import com.carboat.scamdetector.model.advert.Advert;
import com.carboat.scamdetector.service.ScamService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Main class for Scam detector CLI.
 *
 * By default we process advert-to-test.json from the classpath.
 * If a positional argument is given on the cli, we use that instead.
 */
public class ScamDetectorCli {
    private static final String FILE_NAME = "/advert-to-test.json";

    private final InputStream stream;
    private final ObjectMapper objectMapper;
    private final ScamService scamService;

    public ScamDetectorCli(InputStream stream) {
        ScamDetectorApplication application = DaggerScamDetectorApplication.create();
        this.objectMapper = application.objectMapper();
        this.scamService = application.scamService();
        this.stream = stream;
    }

    public static void main(String... args) throws Exception {
        InputStream stream;

        if(args.length > 0){
            stream = Files.newInputStream(Paths.get(args[0]));
        } else {
            stream = ScamDetectorCli.class.getResourceAsStream(FILE_NAME);
        }

        new ScamDetectorCli(stream).run();
    }

    public void run() throws Exception {
        var advert = objectMapper.readValue(stream, Advert.class);
        var report = scamService.checkAdvert(advert);
        objectMapper.writeValue(System.out, report);
    }
}
