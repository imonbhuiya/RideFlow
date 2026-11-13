package com.rideflow.model;

import com.rideflow.enums.RideStatus;
import com.rideflow.enums.RideType;
import java.util.Objects;

/** A ride owns its lifecycle; callers cannot set arbitrary statuses. */
public final class Ride {
    private final String id;
    private final Passenger passenger;
    private final RideType type;
    private Driver driver;
    private RideStatus status = RideStatus.SEARCHING;

    public Ride(String id, Passenger passenger, RideType type) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Ride id is required");
        }
        this.id = id;
        this.passenger = Objects.requireNonNull(passenger, "Passenger is required");
        this.type = Objects.requireNonNull(type, "Ride type is required");
    }

    public void accept(Driver driver) {
        requireStatus(RideStatus.SEARCHING);
        Driver selectedDriver = Objects.requireNonNull(driver, "Driver is required");
        selectedDriver.assignToRide(id, type);
        this.driver = selectedDriver;
        status = RideStatus.ACCEPTED;
    }

    public void markDriverArriving() {
        requireStatus(RideStatus.ACCEPTED);
        status = RideStatus.DRIVER_ARRIVING;
    }

    public void markDriverArrived() {
        requireStatus(RideStatus.DRIVER_ARRIVING);
        status = RideStatus.DRIVER_ARRIVED;
    }

    public void start() {
        requireStatus(RideStatus.DRIVER_ARRIVED);
        status = RideStatus.IN_PROGRESS;
    }

    public void complete() {
        requireStatus(RideStatus.IN_PROGRESS);
        driver.releaseFromRide(id);
        status = RideStatus.COMPLETED;
    }

    public void cancel() {
        if (status == RideStatus.IN_PROGRESS || status == RideStatus.COMPLETED
                || status == RideStatus.CANCELLED) {
            throw new IllegalStateException("Cannot cancel ride in status " + status);
        }
        if (driver != null) {
            driver.releaseFromRide(id);
        }
        status = RideStatus.CANCELLED;
    }

    private void requireStatus(RideStatus expected) {
        if (status != expected) {
            throw new IllegalStateException("Expected " + expected + " but was " + status);
        }
    }

    public String getId() { return id; }
    public Passenger getPassenger() { return passenger; }
    public RideType getType() { return type; }
    public Driver getDriver() { return driver; }
    public RideStatus getStatus() { return status; }
}

