package com.rideflow.model;

import com.rideflow.enums.RideType;

public class Vehicle {
    private String id;
    private String model;
    private String numberPlate;
    private String color;
    private RideType rideType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public RideType getRideType() {
        return rideType;
    }

    public void setRideType(RideType rideType) {
        this.rideType = rideType;
    }

    public Vehicle(String id, String model, String numberPlate, String color, RideType rideType){
        this.id = id;
        this.model = model;
        this.numberPlate = numberPlate;
        this.color = color;
        this.rideType = rideType;


    }

}
