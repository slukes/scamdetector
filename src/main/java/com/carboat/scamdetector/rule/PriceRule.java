package com.carboat.scamdetector.rule;

import com.carboat.scamdetector.model.advert.Advert;
import com.carboat.scamdetector.service.QuotationService;

import javax.inject.Inject;

/**
 * Rule to check that the price is with in an acceptable range of the quote
 */
public class PriceRule implements Rule {
    private static final String NAME = "rule::price::quotation_rate";

    private final QuotationService quotationService;

    @Inject
    PriceRule(QuotationService quotationService){
        this.quotationService = quotationService;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean isPotentialScam(Advert advert) {
        var quote = quotationService.getValue(advert.getVehicle());

        var difference = Math.abs(quote - advert.getPrice());

        return difference > quote * 0.2;
    }
}
