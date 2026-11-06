package com.rideflow.service;

import com.rideflow.enums.RideType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/** Demonstration EUR rates, not live transport prices. */
public final class FareCalculator {
    public BigDecimal estimate(RideType type, BigDecimal distanceKm) {
        Objects.requireNonNull(type, "Ride type is required");
        Objects.requireNonNull(distanceKm, "Distance is required");
        if (distanceKm.signum() <= 0) {
            throw new IllegalArgumentException("Distance must be positive");
        }
        BigDecimal rate = switch (type) {
            case X -> new BigDecimal("1.50");
            case XL -> new BigDecimal("2.00");
            case XXL -> new BigDecimal("2.50");
        };
        return new BigDecimal("3.00").add(rate.multiply(distanceKm))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
