package com.rideflow;

import com.rideflow.enums.*;
import com.rideflow.model.*;
import com.rideflow.service.FareCalculator;
import java.math.BigDecimal;

/** Dependency-free regression checks. Run with java; does not require -ea. */
public final class RideFlowChecks {
    private static int checks;
    public static void main(String[] args) {
        Passenger passenger = new Passenger("p1", "Passenger", "p@example.com", "1", "Frankfurt");
        Vehicle vehicle = new Vehicle("v1", "Golf", "F-RF 1", "Black", RideType.X);
        Driver driver = new Driver("d1", "Driver", "d@example.com", "2",
                "Frankfurt", "licence", vehicle);
        driver.goOnline();
        Ride ride = new Ride("r1", passenger, RideType.X);
        check(ride.getStatus() == RideStatus.SEARCHING);
        rejects(IllegalStateException.class, ride::start);
        rejects(NullPointerException.class, () -> ride.accept(null));
        check(ride.getStatus() == RideStatus.SEARCHING);
        ride.accept(driver);
        check(ride.getDriver() == driver);
        check(driver.getStatus() == DriverStatus.BUSY);
        check(driver.getCurrentRideId().equals("r1"));
        rejects(IllegalStateException.class, driver::goOffline);
        rejects(IllegalStateException.class, driver::goOnline);
        rejects(IllegalStateException.class, () -> driver.setVehicle(vehicle));
        Ride competing = new Ride("r2", passenger, RideType.X);
        rejects(IllegalStateException.class, () -> competing.accept(driver));
        check(competing.getStatus() == RideStatus.SEARCHING);
        rejects(IllegalStateException.class, () -> ride.accept(driver));
        ride.markDriverArriving();
        ride.markDriverArrived();
        ride.start();
        rejects(IllegalStateException.class, ride::cancel);
        ride.complete();
        check(ride.getStatus() == RideStatus.COMPLETED);
        check(driver.getStatus() == DriverStatus.ONLINE);
        check(driver.getCurrentRideId() == null);
        rejects(IllegalStateException.class, ride::complete);
        rejects(IllegalStateException.class, ride::cancel);
        Feedback feedback = new Feedback(ride, 5, " Great ride ");
        check(feedback.getComment().equals("Great ride"));
        check(feedback.getRideId().equals("r1"));
        rejects(IllegalArgumentException.class, () -> new Feedback(ride, 0, ""));
        rejects(IllegalArgumentException.class, () -> new Feedback(ride, 6, ""));
        for (int stage = 0; stage < 4; stage++) {
            Ride cancelled = new Ride("cancel-" + stage, passenger, RideType.X);
            if (stage >= 1) cancelled.accept(driver);
            if (stage >= 2) cancelled.markDriverArriving();
            if (stage >= 3) cancelled.markDriverArrived();
            cancelled.cancel();
            check(cancelled.getStatus() == RideStatus.CANCELLED);
            check(driver.getStatus() == DriverStatus.ONLINE);
            rejects(IllegalStateException.class, cancelled::cancel);
            rejects(IllegalStateException.class, () -> cancelled.accept(driver));
            rejects(IllegalStateException.class, () -> new Feedback(cancelled, 5, ""));
        }
        Driver xlDriver = new Driver("d2", "XL Driver", "xl@example.com", "3",
                "Frankfurt", "licence-2",
                new Vehicle("v2", "Van", "F-RF 2", "White", RideType.XL));
        xlDriver.goOnline();
        Ride categoryMismatch = new Ride("mismatch", passenger, RideType.X);
        rejects(IllegalStateException.class, () -> categoryMismatch.accept(xlDriver));
        check(categoryMismatch.getStatus() == RideStatus.SEARCHING);
        check(xlDriver.getStatus() == DriverStatus.ONLINE);
        FareCalculator fares = new FareCalculator();
        check(fares.estimate(RideType.X, new BigDecimal("10")).equals(new BigDecimal("18.00")));
        check(fares.estimate(RideType.XL, new BigDecimal("10")).equals(new BigDecimal("23.00")));
        check(fares.estimate(RideType.XXL, new BigDecimal("10")).equals(new BigDecimal("28.00")));
        check(fares.estimate(RideType.X, new BigDecimal("0.01")).equals(new BigDecimal("3.02")));
        rejects(IllegalArgumentException.class, () -> fares.estimate(RideType.X, BigDecimal.ZERO));
        rejects(IllegalArgumentException.class, () -> fares.estimate(RideType.X, BigDecimal.ONE.negate()));
        rejects(IllegalArgumentException.class, () -> new Ride(" ", passenger, RideType.X));
        System.out.println("Passed " + checks + " regression checks");
    }
    private static void check(boolean condition) {
        if (!condition) throw new AssertionError("Check failed");
        checks++;
    }
    private static void rejects(Class<? extends Throwable> expected, Runnable action) {
        try { action.run(); }
        catch (Throwable failure) {
            if (!expected.isInstance(failure)) throw new AssertionError("Unexpected exception", failure);
            checks++;
            return;
        }
        throw new AssertionError("Expected " + expected.getSimpleName());
    }
}

