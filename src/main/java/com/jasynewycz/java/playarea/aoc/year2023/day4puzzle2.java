package com.jasynewycz.java.playarea.aoc.year2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

public class day4puzzle2 {

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2023/day4puzzle1realdata.txt"));

        Set<String> winningNums = new HashSet<>();

        List<Integer> winningNumberCounts = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();

        // start with 1 of each card
        int[] gameCounts = new int[lines.size()];
        for(int x = 0; x < gameCounts.length; x++) {
            gameCounts[x] = 1;
        }

        long total = 0;

        for (String line : lines) {
            int ticketTotal = 0;

            String[] parts = line.split("[:|]");
            String[] results = parts[1].split(" ");
            for (String result : results) {
                if (!result.isEmpty()) {
                    winningNums.add(result);
                }
            }
            String[] ticketNumbers = parts[2].split(" ");

            for (String number : ticketNumbers) {
                if (winningNums.contains(number)) {
                     ticketTotal++;
                }
            }

            winningNumberCounts.add(ticketTotal);
            winningNums.clear();
        }

        int gameIndex = 0;

        System.out.println(winningNumberCounts);

        // unboxing :(
        for(int number : winningNumberCounts) {

            int cardsAtCurrent = gameCounts[gameIndex];
            for(int x = gameIndex+1; x < winningNumberCounts.size() && x <= gameIndex+number; x++) {
                gameCounts[x] += cardsAtCurrent;
            }
            gameIndex++;
        }

        System.out.println(Arrays.toString(gameCounts));
        System.out.println(IntStream.of(gameCounts).sum());
    }
}
