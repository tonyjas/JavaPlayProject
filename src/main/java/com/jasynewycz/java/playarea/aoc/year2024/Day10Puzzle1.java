package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Day10Puzzle1 {

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day10puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            int[][] map = new int[lines.length][lines[0].length()];

            for (int x = 0; x < map.length; x++) {
                for(int y = 0; y < map[x].length; y++) {

                    map[x][y] = Integer.parseInt(""+lines[x].charAt(y));
                }
            }

            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[x].length; y++) {

                    if(map[x][y] == 0) {
                        Set<String> items = new HashSet<>();
                        findTrails(map, x, y, 0, items);
                        total += items.size();
                    }
                }
            }
            System.out.println("Total: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int findTrails(int[][] map, int x, int y, int score, Set<String> items) {

        int current = map[x][y];
        if(current == 9) {
            items.add(x + "-" + y);
        }
        int next = current+1;
        if(x > 0 && map[x-1][y] == next) {
            score += findTrails(map, x - 1, y, score, items);
        }
        if(x+1 < map.length && map[x+1][y] == next) {
            score += findTrails(map, x + 1, y, score, items);
        }
        if(y > 0 && map[x][y-1] == next) {
            score += findTrails(map, x, y - 1, score, items);
        }
        if(y+1 < map.length && map[x][y+1] == next) {
            score += findTrails(map, x, y + 1, score, items);
        }
        return score;
    }
}
