package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day1Puzzle2 {

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day1puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            List<Integer> first = new ArrayList<>();
            List<Integer> second = new ArrayList<>();


            for (String line: lines) {
                String[] items = line.split(" ");
                first.add(Integer.parseInt(items[0]));
                second.add(Integer.parseInt(items[items.length-1]));
            }

            Collections.sort(first);
            Collections.sort(second);
            Map<Integer, Integer> counts = new HashMap<>();

            for (int x = 0; x < second.size(); x++) {

                if (counts.computeIfPresent(second.get(x), (key, value) -> value+1) == null) {
                   counts.put(second.get(x), 1);
                }
            }

            for (int x = 0; x < first.size(); x++) {

                total += ((long) first.get(x) * counts.getOrDefault(first.get(x), 0));
            }

            System.out.println("Total is: " + total);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
