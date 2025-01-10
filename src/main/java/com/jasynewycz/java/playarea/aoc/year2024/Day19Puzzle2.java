package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day19Puzzle2 {

    static HashMap<String, Long> cache = new HashMap<>();

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day19puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            Set<String> originalPatterns = Arrays.stream(lines[0].split(", ")).collect(Collectors.toSet());
            Set<String> removablePatterns = new HashSet<>();

            // reduce number of patterns by removing patterns that can be made of smaller patterns
            for (String pattern : originalPatterns) {
                Set<String> temp = new HashSet<>(originalPatterns);
                temp.remove(pattern);
                if(solve(temp, new HashSet<>(), new ArrayList<>(), pattern)) {
                    removablePatterns.add(pattern);
                }
            }

            System.out.println(originalPatterns);
            Set<String> patterns = new HashSet<>(originalPatterns);
            patterns.removeAll(removablePatterns);
            System.out.println(patterns);


            Set<String> failedPatterns = new HashSet<>();

            for (int x = 2; x < lines.length; x++) {

                List<String> tokens = new ArrayList<>();

                if(solve(patterns, failedPatterns, tokens, lines[x])) {
                    System.out.println("********************************************************************************************************************************************");
                    System.out.println(lines[x]);
                    tokens = tokens.reversed();
                    System.out.println(tokens);

                    long score = getPermutations(tokens, originalPatterns, 0);

                    score++;

                    System.out.println("Total: " + score);
                    total += score;
                    System.out.println("********************************************************************************************************************************************");
                }
            }

            System.out.println("Result: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static long getPermutations(List<String> tokens, Set<String> originalPatterns, long total) {
        for (int i = 0; i < tokens.size()-1; i++) {
            String temp = tokens.get(i);

            for (int j = i+1; j < tokens.size(); j++) {
                temp += tokens.get(j);
                if (temp.length() > 7) {
                    break;
                }
                if(originalPatterns.contains(temp)) {
                    total++;
                    List<String> newTokens = new ArrayList<>();
                    newTokens.addAll(tokens.subList(j+1, tokens.size()));
                    if(cache.containsKey(newTokens.toString())) {
                        total+= cache.get(newTokens.toString());
                    } else {

                        long score = getPermutations(newTokens, originalPatterns, 0);
                        total += score;
                        cache.put(newTokens.toString(), score);
                    }
                }
            }
        }
        return total;
    }


    private static boolean solve(Set<String> patterns, Set<String> failedPatterns, List<String> tokens, String design) {

        if(design.length() < 9 && patterns.contains(design)) {
            tokens.add(design);
            return true;
        }

        for (int x = Math.min(8, design.length()); x > 0; x--) {
            String temp = design.substring(0, x);
            if(failedPatterns.contains(temp)) {
                continue;
            }
            if(patterns.contains(temp)) {
                if (design.substring(x).isEmpty()) {
                    tokens.add(temp);
                    return true;
                }
                if(solve(patterns, failedPatterns, tokens, design.substring(x))) {
                    tokens.add(temp);
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
