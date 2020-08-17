package com.carboat.scamdetector.rule;

import com.carboat.scamdetector.model.advert.Advert;

/**
 * Abstract rule for checking the length of a field.
 */
public abstract class AbstractLengthRule implements Rule {
    private static final int MIN_LENGTH = 2;

    abstract String getValue(Advert advert);

    @Override
    public boolean isPotentialScam(Advert advert) {
        var value = getValue(advert);
        return value == null || value.length() < MIN_LENGTH;
    }
}
