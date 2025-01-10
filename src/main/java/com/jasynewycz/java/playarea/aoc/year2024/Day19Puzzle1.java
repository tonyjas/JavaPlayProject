package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Day19Puzzle1 {


    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day19puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            Set<String> patterns = Arrays.stream(lines[0].split(", ")).collect(Collectors.toSet());
            Set<String> removablePatterns = new HashSet<>();

            // reduce number of patterns by removing patterns that can be made of smaller patterns
            for (String pattern : patterns) {
                Set<String> temp = new HashSet<>(patterns);
                temp.remove(pattern);
                if(solve(temp, new HashSet<>(), pattern)) {
                    removablePatterns.add(pattern);
                }
            }

            System.out.println(patterns);
            patterns.removeAll(removablePatterns);
            System.out.println(patterns);


            Set<String> failedPatterns = new HashSet<>();

            for (int x = 2; x < lines.length; x++) {

                if(solve(patterns, failedPatterns, lines[x])) {
                    total++;
                }
            }

            System.out.println("Result: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static boolean solve(Set<String> patterns, Set<String> failedPatterns, String design) {

        if(design.length() < 9 && patterns.contains(design)) {
            return true;
        }

        for (int x = Math.min(8, design.length()); x > 0; x--) {
            String temp = design.substring(0, x);
            if(failedPatterns.contains(temp)) {
                continue;
            }
            if(patterns.contains(temp)) {
                if (design.substring(x).isEmpty()) {
                    return true;
                }
                if(solve(patterns, failedPatterns, design.substring(x))) {
                    return true;
                }
            } else {
                failedPatterns.add(temp);
            }
        }

        /*for (String pattern : patterns) {
            if(design.equals(pattern)) {
                return true;
            } else if (design.startsWith(pattern)) {
                if(solve(patterns, design.substring(pattern.length()))) {
                    return true;
                }
            }
        }

         */
        return false;
    }
}
