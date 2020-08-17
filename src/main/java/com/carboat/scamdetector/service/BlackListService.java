package com.carboat.scamdetector.service;

import com.carboat.scamdetector.model.advert.Vehicle;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

/**
 * Check if a registration plate is in the black list
 */
public class BlackListService {
    private static final String BLACK_LISTED_REGISTRATION = "AA123AB";

    @Inject
    public BlackListService() {
        // For dagger
    }

    /**
     * Check if a registration plate is in the black list
     *
     * @param vehicle the vehicle to check
     * @return true if blacklisted
     */
    public boolean isBlackListed(Vehicle vehicle) {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        return vehicle.getRegisterNumber().equals(BLACK_LISTED_REGISTRATION);

    }
}
