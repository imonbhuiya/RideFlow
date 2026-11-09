package com.rideflow;

import com.rideflow.enums.*;
import com.rideflow.model.*;
import com.rideflow.repository.RideRepository;
import com.rideflow.service.RideQueries;

public final class RideQueryChecks {
    public static void main(String[] args) {
        var repository = new RideRepository();
        var queries = new RideQueries(repository);
        if (!queries.active().isEmpty()) throw new AssertionError();
        var p = new Passenger("p", "Name", "email", "phone", "address");
        var first = new Ride("one", p, RideType.X);
        var second = new Ride("two", p, RideType.X);
        repository.add(first);
        repository.add(second);
        var snapshot = queries.active();
        first.cancel();
        if (queries.active().size() != 1 || queries.active().get(0) != second)
            throw new AssertionError("Cancelled ride remained active");
        if (queries.byStatus(RideStatus.CANCELLED).get(0) != first)
            throw new AssertionError("Status filter failed");
        if (snapshot.size() != 2) throw new AssertionError("Membership snapshot changed");
        try { snapshot.clear(); throw new AssertionError(); }
        catch (UnsupportedOperationException expected) { }
        try { queries.byStatus(null); throw new AssertionError(); }
        catch (NullPointerException expected) { }
        System.out.println("Passed ride query checks");
    }
}
