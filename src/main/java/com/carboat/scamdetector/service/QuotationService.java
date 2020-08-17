package com.carboat.scamdetector.service;

import com.carboat.scamdetector.model.advert.Vehicle;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

/**
 * Get an estimate for the value of a vehicle
 */
public class QuotationService {

    public static final int MOCK_VALUE = 3500;

    @Inject
    public QuotationService() {
        // For dagger
    }

    /**
     * Get the value of a given
     * @param vehicle the vehicle to estimate
     * @return the quoted value
     */
    @SuppressWarnings("unused") // normal as we are mocking
    public int getValue(Vehicle vehicle) {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        return MOCK_VALUE;
    }
}
