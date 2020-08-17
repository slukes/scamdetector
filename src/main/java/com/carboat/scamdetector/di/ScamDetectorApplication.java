package com.carboat.scamdetector.di;

import com.carboat.scamdetector.service.ScamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Component;

@Component(modules = {JacksonModule.class, RulesModule.class})
public interface ScamDetectorApplication {
    ScamService scamService();

    ObjectMapper objectMapper();
}
