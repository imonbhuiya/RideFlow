package com.rideflow.model;

import com.rideflow.enums.DriverStatus;
import com.rideflow.enums.RideType;
import java.util.Objects;

public class Driver extends User {
    private String drivingLicence;
    private Vehicle vehicle;
    private DriverStatus status = DriverStatus.OFFLINE;
    private String currentRideId;

    public Driver(String id, String name, String email, String phone, String address,String drivingLicence, Vehicle vehicle) {

        super(id, name, email, phone, address);

        this.drivingLicence = drivingLicence;
        this.vehicle = vehicle;
    }

    public String getDrivingLicence() {
        return drivingLicence;
    }

    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        if (status == DriverStatus.BUSY) {
            throw new IllegalStateException("Cannot change vehicle during an active assignment");
        }
        this.vehicle = vehicle;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public String getCurrentRideId() {
        return currentRideId;
    }

    public void goOnline() {
        if (status == DriverStatus.BUSY) {
            throw new IllegalStateException("Driver already has an active assignment");
        }
        if (vehicle == null || vehicle.getRideType() == null) {
            throw new IllegalStateException("A categorized vehicle is required to go online");
        }
        status = DriverStatus.ONLINE;
    }

    public void goOffline() {
        if (status == DriverStatus.BUSY) {
            throw new IllegalStateException("Cannot go offline during an active assignment");
        }
        status = DriverStatus.OFFLINE;
    }

    public boolean canAccept(RideType rideType) {
        Objects.requireNonNull(rideType, "Ride type is required");
        return status == DriverStatus.ONLINE
                && vehicle != null
                && vehicle.supports(rideType);
    }

    void assignToRide(String rideId, RideType rideType) {
        if (!canAccept(rideType)) {
            throw new IllegalStateException("Driver is not available for " + rideType);
        }
        currentRideId = Objects.requireNonNull(rideId, "Ride id is required");
        status = DriverStatus.BUSY;
    }

    void releaseFromRide(String rideId) {
        if (status != DriverStatus.BUSY || !Objects.equals(currentRideId, rideId)) {
            throw new IllegalStateException("Driver is not assigned to ride " + rideId);
        }
        currentRideId = null;
        status = DriverStatus.ONLINE;
    }
}

