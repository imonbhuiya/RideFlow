package com.rideflow.repository;

import com.rideflow.model.Feedback;
import java.util.*;

/** One feedback entry per completed ride, held for this process only. */
public final class FeedbackRepository {
    private final Map<String, Feedback> entries = new LinkedHashMap<>();

    public void add(Feedback feedback) {
        Objects.requireNonNull(feedback, "Feedback is required");
        if (entries.putIfAbsent(feedback.getRideId(), feedback) != null) {
            throw new IllegalArgumentException("Feedback already exists for ride " + feedback.getRideId());
        }
    }

    public Optional<Feedback> findByRideId(String rideId) {
        return Optional.ofNullable(entries.get(Objects.requireNonNull(rideId, "Ride id is required")));
    }

    public List<Feedback> findAll() {
        return List.copyOf(entries.values());
    }
}
