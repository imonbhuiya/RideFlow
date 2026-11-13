package com.rideflow;

import com.rideflow.enums.DriverStatus;
import com.rideflow.enums.RideType;
import com.rideflow.model.Driver;
import com.rideflow.model.Vehicle;

/** Dependency-free checks for driver availability and category matching. */
public final class DriverAvailabilityChecks {
    private static int checks;

    public static void main(String[] args) {
        Vehicle car = new Vehicle("v1", "Golf", "F-RF 1", "Black", RideType.X);
        Driver driver = new Driver("d1", "Driver", "d@example.com", "1",
                "Frankfurt", "licence", car);

        check(driver.getStatus() == DriverStatus.OFFLINE);
        check(!driver.canAccept(RideType.X));
        driver.goOnline();
        check(driver.getStatus() == DriverStatus.ONLINE);
        check(driver.canAccept(RideType.X));
        check(!driver.canAccept(RideType.XL));
        rejects(NullPointerException.class, () -> driver.canAccept(null));
        driver.goOffline();
        check(driver.getStatus() == DriverStatus.OFFLINE);

        Driver withoutVehicle = new Driver("d2", "Driver", "d2@example.com", "2",
                "Frankfurt", "licence", null);
        rejects(IllegalStateException.class, withoutVehicle::goOnline);

        System.out.println("Passed " + checks + " driver availability checks");
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
