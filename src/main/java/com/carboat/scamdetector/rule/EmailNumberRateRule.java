package com.carboat.scamdetector.rule;

import com.carboat.scamdetector.model.advert.Advert;

/**
 * Rule to validate the numeric ratio of the email address
 */
public class EmailNumberRateRule implements Rule {
    private static final String NAME = "rule::mail::number_rate";

    private static final float MAX_NUMBER_RATE = 0.3f;

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
        var maxNumbers = Math.round(beforeAt.length() * MAX_NUMBER_RATE);

        var count = beforeAt.chars()
                .filter(Character::isDigit)
                .limit(maxNumbers + 1)
                .count();

        return count > maxNumbers;
    }

    private boolean isInvalid(String email) {
        return email == null || email.isEmpty() || !email.contains("@");
    }
}
