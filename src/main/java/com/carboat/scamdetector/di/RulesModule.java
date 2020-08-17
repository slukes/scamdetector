package com.carboat.scamdetector.di;

import com.carboat.scamdetector.rule.*;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

/**
 * Dagger provider for all rules
 */
@Module
class RulesModule {
    @Provides
    @IntoSet
    Rule blackListRule(BlackListRule blackListRule) {
        return blackListRule;
    }

    @Provides
    @IntoSet
    Rule emailAlphaRateRule() {
        return new EmailAlphaRateRule();
    }

    @Provides
    @IntoSet
    Rule emailNumberRateRule() {
        return new EmailNumberRateRule();
    }

    @Provides
    @IntoSet
    Rule firstnameLengthRule() {
        return new FirstnameLengthRule();
    }

    @Provides
    @IntoSet
    Rule lastnameLengthRule() {
        return new LastnameLengthRule();
    }

    @Provides
    @IntoSet
    Rule priceRule(PriceRule priceRule) {
        return priceRule;
    }
}
