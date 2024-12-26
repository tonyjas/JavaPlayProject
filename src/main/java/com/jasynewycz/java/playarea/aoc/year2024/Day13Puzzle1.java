package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day13Puzzle1 {

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day13puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            int butAX = 0;
            int butAY = 0;
            int butBX = 0;
            int butBY = 0;
            int prizeX = 0;
            int prizeY = 0;

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

                    total += calculateResult(butAX, butAY, butBX, butBY, prizeX, prizeY);
                }

            }

            System.out.println("Result: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static int calculateResult(int butAX, int butAY, int butBX, int butBY, int prizeX, int prizeY) {

        int lowScore = Integer.MAX_VALUE;

        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                if(i*butAX + j*butBX == prizeX && i*butAY + j*butBY == prizeY && ((i*3) + j) < lowScore) {
                    lowScore = i*3 + j;
                }
            }
        }
        return ((lowScore == Integer.MAX_VALUE) ? 0 : lowScore);
    }
}
