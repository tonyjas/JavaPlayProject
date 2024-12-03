package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day2Puzzle1 {

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day2puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            for (String line: lines) {

                int[] values = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();

                boolean safe = true;
                boolean desc = false;
                for (int x = 0; x < values.length-1; x++) {

                    if( x == 0 && values[x] > values[x+1]) {
                        desc = true;
                    }

                    if(desc && values[x] <= values[x+1]) {
                        safe = false;
                        break;
                    }
                    if(!desc && values[x] >= values[x+1]) {
                        safe = false;
                        break;
                    }
                    if(Math.abs(values[x] - values[x+1]) > 3) {
                        safe=false;
                        break;
                    }

                }

                if (safe) {
                    total++;
                }
            }

            System.out.println("Total: " + total);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
