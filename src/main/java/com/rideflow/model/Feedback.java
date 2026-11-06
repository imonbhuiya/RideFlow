package com.rideflow.model;

import com.rideflow.enums.RideStatus;
import java.util.Objects;

/** Immutable feedback snapshot, accepted only for completed rides. */
public final class Feedback {
    private final String rideId;
    private final int rating;
    private final String comment;

    public Feedback(Ride ride, int rating, String comment) {
        Objects.requireNonNull(ride, "Ride is required");
        if (ride.getStatus() != RideStatus.COMPLETED) {
            throw new IllegalStateException("Feedback requires a completed ride");
        }
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.rideId = ride.getId();
        this.rating = rating;
        this.comment = Objects.requireNonNull(comment, "Comment is required").strip();
    }

    public String getRideId() { return rideId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
}
