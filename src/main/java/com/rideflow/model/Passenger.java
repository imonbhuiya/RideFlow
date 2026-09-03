package com.rideflow.model;


//Passenger must inherit from User
public class Passenger extends User {
    //Create a constructor receiving the same five values
    public Passenger(String id, String name, String email, String phone, String address) {
        super(id, name, email, phone, address);
    }
}
