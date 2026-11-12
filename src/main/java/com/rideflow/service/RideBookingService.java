package com.rideflow.service;

import com.rideflow.enums.RideType;
import com.rideflow.model.*;
import com.rideflow.repository.RideRepository;
import java.util.*;

/** Booking operations for a single-threaded console session. */
public final class RideBookingService {
    private final RideRepository repository;

    public RideBookingService(RideRepository repository) {
        this.repository = Objects.requireNonNull(repository, "Repository is required");
    }

    public Ride request(String id, Passenger passenger, RideType type) {
        Ride ride = new Ride(id, passenger, type);
        repository.add(ride);
        return ride;
    }

    public Ride cancel(String id) {
        Ride ride = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ride not found: " + id));
        ride.cancel();
        return ride;
    }
}
