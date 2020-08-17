package com.carboat.scamdetector.model;

import com.carboat.scamdetector.di.JacksonModule;
import com.carboat.scamdetector.model.advert.Advert;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

class AdvertTest {
    private final ObjectMapper objectMapper = JacksonModule.objectMapper();

    @Test
    void testSerialization() throws Exception {
        var inputJson = IOUtils.resourceToString("/test-adverts/good-advert.json", UTF_8);

        var advert = objectMapper.readValue(inputJson, Advert.class);
        var resultJson = objectMapper.writeValueAsString(advert);

        assertEquals(inputJson, resultJson, false);
    }
}
