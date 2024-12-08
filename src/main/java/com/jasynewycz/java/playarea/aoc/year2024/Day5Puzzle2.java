package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day5Puzzle2 {

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day5puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;
            Map<Integer, Set<Integer>> pageForwardOrderRules = new HashMap<>();
            Map<Integer, Set<Integer>> pageBackwardOrderRules = new HashMap<>();

            boolean moreRules = true;
            for(String line: lines) {
                if(line.isBlank()) {
                    moreRules = false;
                    continue;
                }
                if(moreRules) {
                    String[] data = line.split("\\|");
                    Integer first = Integer.valueOf(data[0]);
                    Integer second = Integer.valueOf(data[1]);
                    if(pageForwardOrderRules.containsKey(first)) {
                        pageForwardOrderRules.get(first).add(second);
                    } else {
                        Set<Integer> rules = new TreeSet<>();
                        rules.add(second);
                        pageForwardOrderRules.put(first, rules);
                    }
                    if(pageBackwardOrderRules.containsKey(second)) {
                        pageBackwardOrderRules.get(second).add(first);
                    } else {
                        Set<Integer> rules = new TreeSet<>();
                        rules.add(first);
                        pageBackwardOrderRules.put(second, rules);
                    }

                } else {

                    int[] data = Arrays.stream(line.split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    boolean isGood = true;
                    OUTER: for (int x = 0; x < data.length; x++) {
                        int current = data[x];

                        Set<Integer> rules = pageForwardOrderRules.get(current);
                        for (int y = x+1; y < data.length; y++) {
                            if(rules == null || !rules.contains(data[y])) {
                                isGood = false;
                                int temp = data[x];
                                data[x] = data[y];
                                data[y] = temp;
                                --x;
                                continue OUTER;
                            }
                        }
                    }
                    if(!isGood) {

                        int midIndex = Math.floorDiv(data.length, 2);
                        System.out.println(line + "\t" + data[midIndex]);
                        total += data[midIndex];
                    }
                }
            }
            System.out.println("Total: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
