package com.carboat.scamdetector.rule;

import com.carboat.scamdetector.model.advert.Advert;

/**
 * Rule to validate the alphanumeric ratio of the email address
 */
public class EmailAlphaRateRule implements Rule {
    private static final String NAME = "rule::mail::alpha_rate";

    private static final float MINIMUM_ALPHA_RATE = 0.7f;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean isPotentialScam(Advert advert) {
        var email = advert.getContacts().getEmail();

        if(isInvalid(email)){
            return true;
        }

        var beforeAt = email.split("@")[0];

        var minAlphaChars = Math.round(beforeAt.length() * MINIMUM_ALPHA_RATE);

        var count = beforeAt.chars()
                .filter(Character::isLetterOrDigit)
                .limit(minAlphaChars)
                .count();

        return count < minAlphaChars;
    }

    private boolean isInvalid(String email) {
        return email == null || email.isEmpty() || ! email.contains("@");
    }
}
