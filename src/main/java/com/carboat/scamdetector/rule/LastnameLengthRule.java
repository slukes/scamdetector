package com.carboat.scamdetector.rule;

import com.carboat.scamdetector.model.advert.Advert;

/**
 * Rule to check the length of the first name
 */
public class LastnameLengthRule extends AbstractLengthRule {
    private static final String NAME = "rule::lastname::length";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    String getValue(Advert advert) {
        return advert.getContacts().getLastName();
    }
}
