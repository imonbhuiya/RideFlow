package com.rideflow;

import com.rideflow.repository.RideRepository;
import com.rideflow.model.*;
import com.rideflow.enums.RideType;

public final class RepositoryChecks {
    public static void main(String[] args) {
        var repository = new RideRepository();
        var passenger = new Passenger("p", "Name", "email", "phone", "address");
        var first = new Ride("one", passenger, RideType.X);
        var second = new Ride("two", passenger, RideType.XL);
        check(repository.findAll().isEmpty());
        check(repository.findById("missing").isEmpty());
        repository.add(first);
        var snapshot = repository.findAll();
        repository.add(second);
        check(snapshot.size() == 1);
        check(repository.findAll().get(1) == second);
        check(repository.findById("one").orElseThrow() == first);
        try {
            repository.add(new Ride("one", passenger, RideType.XXL));
            throw new AssertionError("Duplicate accepted");
        } catch (IllegalArgumentException expected) { }
        check(repository.findById("one").orElseThrow() == first);
        try {
            snapshot.clear();
            throw new AssertionError("Mutable snapshot");
        } catch (UnsupportedOperationException expected) { }
        try {
            repository.add(null);
            throw new AssertionError("Null accepted");
        } catch (NullPointerException expected) { }
        System.out.println("Passed repository checks");
    }
    private static void check(boolean value) {
        if (!value) throw new AssertionError();
    }
}
