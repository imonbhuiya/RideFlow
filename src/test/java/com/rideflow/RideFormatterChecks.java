package com.rideflow;

import com.rideflow.app.RideFormatter;
import com.rideflow.model.*;
import com.rideflow.enums.RideType;

public final class RideFormatterChecks {
    public static void main(String[] args) {
        var formatter = new RideFormatter();
        var passenger = new Passenger("p", "Private Name", "secret@example.com", "123", "Private Address");
        var ride = new Ride("r1", passenger, RideType.XL);
        if (!formatter.format(ride).equals("Ride r1 | XL | SEARCHING"))
            throw new AssertionError("Unexpected summary");
        ride.cancel();
        if (!formatter.format(ride).equals("Ride r1 | XL | CANCELLED"))
            throw new AssertionError("Stale status");
        var injected = new Ride("r\n2\t", passenger, RideType.X);
        if (!formatter.format(injected).equals("Ride r 2  | X | SEARCHING"))
            throw new AssertionError("Control characters not sanitized");
        try { formatter.format(null); throw new AssertionError("Null accepted"); }
        catch (NullPointerException expected) { }
        System.out.println("Passed ride formatter checks");
    }
}
