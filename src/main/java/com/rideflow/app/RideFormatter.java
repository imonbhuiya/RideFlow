package com.rideflow.app;

import com.rideflow.model.Ride;
import java.util.Objects;

/** A compact console summary that avoids exposing passenger contact details. */
public final class RideFormatter {
    public String format(Ride ride) {
        Objects.requireNonNull(ride, "Ride is required");
        return "Ride " + singleLine(ride.getId()) + " | " + ride.getType()
                + " | " + ride.getStatus();
    }

    private String singleLine(String value) {
        return value.replaceAll("\\p{Cntrl}", " ");
    }
}
