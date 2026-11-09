package com.rideflow.service;

import com.rideflow.enums.RideStatus;
import com.rideflow.model.Ride;
import com.rideflow.repository.RideRepository;
import java.util.*;

/** Read operations for console ride lists. Results preserve request order. */
public final class RideQueries {
    private final RideRepository repository;

    public RideQueries(RideRepository repository) {
        this.repository = Objects.requireNonNull(repository, "Repository is required");
    }

    public List<Ride> byStatus(RideStatus status) {
        Objects.requireNonNull(status, "Status is required");
        return repository.findAll().stream().filter(r -> r.getStatus() == status).toList();
    }

    public List<Ride> active() {
        return repository.findAll().stream()
                .filter(r -> r.getStatus() != RideStatus.COMPLETED
                        && r.getStatus() != RideStatus.CANCELLED).toList();
    }
}
