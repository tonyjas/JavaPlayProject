package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day7Puzzle2 {


    public static void main(String[] args) {


        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day7puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            for (String line: lines) {

                String[] parts = line.split(":");

                long result = Long.parseLong(parts[0]);

                parts = parts[1].split(" ");
                long[] values = Arrays.stream(parts).filter(s -> !s.isBlank()).mapToLong(Long::parseLong).toArray();

                total += calc(result, values[0], values, 1);

            }

            System.out.println("Total: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static long calc(long target, long total, long[] values, int index) {
        if(index >= values.length) {
            if(target == total) {
                return target;
            } else {
                return 0;
            }
        }

        if(calc(target, total+values[index], values, index+1) == target) {
            return target;
        }
        if(calc(target, total*values[index], values, index+1) == target) {
            return target;
        }
        if(calc(target, Long.parseLong(""+total+values[index]), values, index+1) == target) {
            return target;
        }

        return 0;
    }

}
