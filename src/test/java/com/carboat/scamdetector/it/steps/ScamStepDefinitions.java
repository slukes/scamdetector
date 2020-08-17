package com.carboat.scamdetector.it.steps;

import com.carboat.scamdetector.ScamDetectorCli;
import com.carboat.scamdetector.model.ScamReport;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ScamStepDefinitions {
    private enum TestAdverts {
        NON_SCAM("/test-adverts/good-advert.json", Collections.emptyList()),

        SCAM_FIRST_NAME("/test-adverts/bad-firstname-advert.json", List.of("rule::firstname::length")),

        ALL_SCAM("/test-adverts/bad-advert.json", List.of("rule::registernumber::blacklist",
                "rule::mail::alpha_rate", "rule::mail::number_rate", "rule::firstname::length",
                "rule::lastname::length", "rule::price::quotation_rate"
        ));

        private final String fileName;
        private final List<String> expectedErrors;

        TestAdverts(String fileName, List<String> expectedErrors) {
            this.fileName = fileName;
            this.expectedErrors = expectedErrors;
        }
    }

    private TestAdverts advert;
    private ScamReport output;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Given("a non scam advert")
    public void setNonScamAdvert() {
        this.advert = TestAdverts.NON_SCAM;
    }

    @Given("an advert with an invalid firstname")
    public void setInvalidFirstNameAdvert() {
      this.advert = TestAdverts.SCAM_FIRST_NAME;
    }

    @Given("an advert with all of the rules broken")
    public void setBadAdvert() {
        // Write code here that turns the phrase above into concrete actions
        this.advert = TestAdverts.ALL_SCAM;
    }

    @When("I launch the scam detector")
    public void launchDetector() throws Exception {
        var scamDetector = new ScamDetectorCli(getClass().getResourceAsStream(this.advert.fileName));
        var out = tapSystemOut(scamDetector::run);
        this.output = objectMapper.readValue(out, ScamReport.class);
    }

    @Then("there are no errors")
    public void assertNoErrors() {
        assertAll(
                () -> assertFalse(output.isScam()),
                () -> assertTrue(output.getRules().isEmpty())
        );
    }

    @Then("^I can see the errors?")
    public void assertErrors() {
        assertAll(
                () -> assertTrue(output.isScam()),
                () -> assertThat(output.getRules()).containsExactlyInAnyOrderElementsOf(this.advert.expectedErrors)
        );
    }
}
