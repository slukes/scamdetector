package com.carboat.scamdetector.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * Class to represent the output of the script
 */
public class ScamReport {
    private final String reference;
    private final boolean scam;
    private final List<String> rules;

    @JsonCreator
    public ScamReport(@JsonProperty("reference") String reference,
                      @JsonProperty("scam") boolean scam,
                      @JsonProperty("rules") List<String> rules) {
        this.reference = reference;
        this.scam = scam;
        this.rules = rules;
    }

    public String getReference() {
        return reference;
    }

    public boolean isScam() {
        return scam;
    }

    public List<String> getRules() {
        return rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScamReport that = (ScamReport) o;
        return scam == that.scam &&
                Objects.equals(reference, that.reference) &&
                Objects.equals(rules, that.rules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, scam, rules);
    }

    @Override
    public String toString() {
        return "ScamReport{" +
                "reference='" + reference + '\'' +
                ", scam=" + scam +
                ", rules=" + rules +
                '}';
    }
}
