package com.rideflow;

import com.rideflow.enums.RideType;
import com.rideflow.model.Vehicle;

/** Dependency-free checks for vehicle category matching. */
public final class VehicleChecks {
    private static int checks;

    public static void main(String[] args) {
        Vehicle car = new Vehicle("v1", "Golf", "F-RF 1", "Black", RideType.X);

        check(car.supports(RideType.X));
        check(!car.supports(RideType.XL));
        rejects(NullPointerException.class, () -> car.supports(null));

        car.setRideType(RideType.XXL);
        check(car.supports(RideType.XXL));
        check(!car.supports(RideType.X));

        System.out.println("Passed " + checks + " vehicle checks");
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
