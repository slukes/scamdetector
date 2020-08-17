package com.carboat.scamdetector.rule;

import com.carboat.scamdetector.model.advert.Advert;
import com.carboat.scamdetector.service.BlackListService;

import javax.inject.Inject;

/**
 * Rule to check that the car is not in the black list.
 */
public class BlackListRule implements Rule {
    private static final String NAME = "rule::registernumber::blacklist";

    private final BlackListService blackListService;

    @Inject
    BlackListRule(BlackListService blackListService){
        this.blackListService = blackListService;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean isPotentialScam(Advert advert) {
        return blackListService.isBlackListed(advert.getVehicle());
    }
}
