package com.rideflow.model;

public class Driver extends User {
    private String drivingLicence;
    private Vehicle vehicle;

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
        this.vehicle = vehicle;
    }
}
