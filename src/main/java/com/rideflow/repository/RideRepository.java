package com.rideflow.repository;

import com.rideflow.model.Ride;
import java.util.*;

/** Single-threaded, process-local storage for the console application. */
public final class RideRepository {
    private final Map<String, Ride> rides = new LinkedHashMap<>();

    public void add(Ride ride) {
        Objects.requireNonNull(ride, "Ride is required");
        if (rides.putIfAbsent(ride.getId(), ride) != null) {
            throw new IllegalArgumentException("Ride already exists: " + ride.getId());
        }
    }

    public Optional<Ride> findById(String id) {
        return Optional.ofNullable(rides.get(Objects.requireNonNull(id, "Id is required")));
    }

    /** Immutable list snapshot; contained rides retain their live lifecycle state. */
    public List<Ride> findAll() {
        return List.copyOf(rides.values());
    }
}
