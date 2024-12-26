package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day13Puzzle2 {
    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day13puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            int butAX = 0;
            int butAY = 0;
            int butBX = 0;
            int butBY = 0;
            long prizeX = 0;
            long prizeY = 0;

            for (String line: lines) {

                if (line.startsWith("Button A:")) {
                    butAX = Integer.parseInt(line.substring(line.indexOf("X+")+2, line.indexOf(',')));
                    butAY = Integer.parseInt(line.substring(line.indexOf("Y+")+2));
                } else if (line.startsWith("Button B:")) {
                    butBX = Integer.parseInt(line.substring(line.indexOf("X+")+2, line.indexOf(',')));
                    butBY = Integer.parseInt(line.substring(line.indexOf("Y+")+2));
                } else if (line.startsWith("Prize:")) {
                    prizeX = Integer.parseInt(line.substring(line.indexOf("X=")+2, line.indexOf(',')));
                    prizeY = Integer.parseInt(line.substring(line.indexOf("Y=")+2));

                    prizeX += 10000000000000L;
                    prizeY += 10000000000000L;

                    total += calculateResult(butAX, butAY, butBX, butBY, prizeX, prizeY);
                }

                System.out.println(total);
            }

            System.out.println("Result: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static long calculateResult(int butAX, int butAY, int butBX, int butBY, long prizeX, long prizeY) {

        try {
            long j = ((butAX * prizeY) - (butAY * prizeX)) / ((butAX * butBY) - (butAY * butBX));
            long i = ((j * (butBY - butBX)) + prizeX - prizeY) / (butAX - butAY);

            if (i * butAX + j * butBX == prizeX && i * butAY + j * butBY == prizeY) {

                return i * 3 + j;
            }
        } catch(ArithmeticException ae) {
            // swallow as this indicates unsolveable (div by zero)
        }
        return 0L;
    }
}
