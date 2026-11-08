package com.rideflow;

import com.rideflow.repository.FeedbackRepository;
import com.rideflow.model.*;
import com.rideflow.enums.RideType;

public final class FeedbackRepositoryChecks {
    public static void main(String[] args) {
        var store = new FeedbackRepository();
        var passenger = new Passenger("p", "Name", "email", "phone", "address");
        var driver = new Driver("d", "Driver", "email", "phone", "address", "licence", null);
        var ride = new Ride("r", passenger, RideType.X);
        ride.accept(driver);
        ride.markDriverArriving();
        ride.markDriverArrived();
        ride.start();
        ride.complete();
        var feedback = new Feedback(ride, 4, "Good");
        var empty = store.findAll();
        store.add(feedback);
        if (!empty.isEmpty() || store.findByRideId("r").orElseThrow() != feedback)
            throw new AssertionError("Lookup or snapshot failed");
        try {
            store.add(new Feedback(ride, 1, "Duplicate"));
            throw new AssertionError("Duplicate accepted");
        } catch (IllegalArgumentException expected) { }
        if (store.findByRideId("r").orElseThrow().getRating() != 4)
            throw new AssertionError("Original replaced");
        if (store.findByRideId("missing").isPresent()) throw new AssertionError();
        try { store.findAll().clear(); throw new AssertionError(); }
        catch (UnsupportedOperationException expected) { }
        try { store.add(null); throw new AssertionError(); }
        catch (NullPointerException expected) { }
        System.out.println("Passed feedback repository checks");
    }
}
