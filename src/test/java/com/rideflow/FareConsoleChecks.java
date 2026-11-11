package com.rideflow;

import com.rideflow.app.FareConsole;
import java.io.*;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public final class FareConsoleChecks {
    public static void main(String[] args) {
        var buffer = new ByteArrayOutputStream();
        FareConsole.run(new Scanner("bad\nX -1\nxl 10\nXXL 2.5\nquit\nX 100\n"),
                new PrintStream(buffer, true, StandardCharsets.UTF_8));
        String text = buffer.toString(StandardCharsets.UTF_8);
        if (!text.contains("Use:") || !text.contains("Invalid category")
                || !text.contains("EUR 23.00") || !text.contains("EUR 9.25")
                || text.contains("EUR 153.00")) throw new AssertionError(text);
        FareConsole.run(new Scanner(""), new PrintStream(new ByteArrayOutputStream()));
        System.out.println("Passed fare console checks");
    }
}
