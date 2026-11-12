package com.rideflow;

import com.rideflow.enums.*;
import com.rideflow.model.*;
import com.rideflow.repository.RideRepository;
import com.rideflow.service.RideBookingService;
import java.util.NoSuchElementException;

public final class BookingChecks {
    public static void main(String[] args) {
        var repository = new RideRepository();
        var service = new RideBookingService(repository);
        var passenger = new Passenger("p", "Name", "email", "phone", "address");
        var ride = service.request("r", passenger, RideType.X);
        if (repository.findById("r").orElseThrow() != ride) throw new AssertionError();
        try { service.request("r", passenger, RideType.XL); throw new AssertionError(); }
        catch (IllegalArgumentException expected) { }
        if (repository.findById("r").orElseThrow() != ride) throw new AssertionError();
        if (service.cancel("r") != ride || ride.getStatus() != RideStatus.CANCELLED)
            throw new AssertionError();
        try { service.cancel("missing"); throw new AssertionError(); }
        catch (NoSuchElementException expected) { }
        try { service.cancel("r"); throw new AssertionError(); }
        catch (IllegalStateException expected) { }
        try { service.request("bad", null, RideType.X); throw new AssertionError(); }
        catch (NullPointerException expected) { }
        if (repository.findAll().size() != 1) throw new AssertionError();
        System.out.println("Passed booking checks");
    }
}
