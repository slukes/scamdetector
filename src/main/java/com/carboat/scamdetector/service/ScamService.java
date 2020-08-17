package com.carboat.scamdetector.service;

import com.carboat.scamdetector.model.ScamReport;
import com.carboat.scamdetector.model.advert.Advert;
import com.carboat.scamdetector.rule.Rule;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Apply all of the rules to find out if an advert is potentially a scam
 */
public class ScamService {

    private final Set<Rule> rules;

    @Inject
    public ScamService(Set<Rule> rules) {
        this.rules = rules;
    }

    /**
     * Check an advert to see if it was a scam
     *
     * @param advert the advert
     * @return a ScamReport
     */
    public ScamReport checkAdvert(Advert advert) {
        var violations = rules.parallelStream()
                .filter(rule -> rule.isPotentialScam(advert))
                .map(Rule::getName)
                .collect(Collectors.toList());

        return new ScamReport(advert.getReference(), !violations.isEmpty(), violations);
    }
}
