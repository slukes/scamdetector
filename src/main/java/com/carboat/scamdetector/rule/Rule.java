package com.carboat.scamdetector.rule;

import com.carboat.scamdetector.model.advert.Advert;

/**
 * Interface to represent a single rule to determine if an advert is a scam
 */
public interface Rule {
    /**
     * The name of the rule which will be returned in the json isPotentialScam == true
     *
     * @return the name
     */
    String getName();

    /**
     * Determine if according to this rule, the advert is a scam
     *
     * @param advert an advert to test
     * @return true if the advert is a potential scam
     */
    boolean isPotentialScam(Advert advert);
}
