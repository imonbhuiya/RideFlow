package com.rideflow.app;

import com.rideflow.enums.RideType;
import com.rideflow.service.FareCalculator;
import java.math.BigDecimal;
import java.util.*;

/** Standalone fare estimator; EOF and "quit" exit cleanly. */
public final class FareConsole {
    public static void main(String[] args) {
        run(new Scanner(System.in), System.out);
    }

    public static void run(Scanner input, java.io.PrintStream output) {
        Objects.requireNonNull(input);
        Objects.requireNonNull(output);
        var calculator = new FareCalculator();
        output.println("RideFlow demo fare estimator. Enter: X|XL|XXL distanceKm; quit to exit.");
        while (input.hasNextLine()) {
            String line = input.nextLine().strip();
            if (line.equalsIgnoreCase("quit")) return;
            String[] parts = line.split("\\s+");
            if (parts.length != 2) {
                output.println("Use: X|XL|XXL positive-distance");
                continue;
            }
            try {
                RideType type = RideType.valueOf(parts[0].toUpperCase(Locale.ROOT));
                BigDecimal distance = new BigDecimal(parts[1]);
                output.println("Estimated fare: EUR " + calculator.estimate(type, distance).toPlainString());
            } catch (IllegalArgumentException exception) {
                output.println("Invalid category or distance. Example: XL 10");
            }
        }
    }
}
