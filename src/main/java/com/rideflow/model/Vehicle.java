package com.rideflow.model;

public class Vehicle {
    private String id;
    private String model;
    private String numberPlate;
    private String color;

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

    public Vehicle(String id, String model, String numberPlate, String color ){
        this.id = id;
        this.model = model;
        this.numberPlate = numberPlate;
        this.color = color;


    }

}
