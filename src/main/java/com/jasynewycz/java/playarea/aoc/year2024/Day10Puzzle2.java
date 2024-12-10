package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Day10Puzzle2 {

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day10puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            int[][] map = new int[lines.length][lines[0].length()];

            for (int x = 0; x < map.length; x++) {
                for(int y = 0; y < map[x].length; y++) {
                    if(lines[x].charAt(y) == '.') {
                        map[x][y] = -1;
                    } else {
                        map[x][y] = Integer.parseInt("" + lines[x].charAt(y));
                    }
                }
            }


            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[x].length; y++) {

                    if(map[x][y] == 0) {
                        int temp = findTrails(map, x, y, 0);
                        System.out.println(temp);
                        total += temp;
                    }
                }
            }

            System.out.println("Total: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static int findTrails(int[][] map, int x, int y, int score) {

        int current = map[x][y];
        if(current == 9) {
            return score+1;
        }
        int next = current+1;
        if(x > 0 && map[x-1][y] == next) {
            score = findTrails(map, x - 1, y, score);
        }
        if(x+1 < map.length && map[x+1][y] == next) {
            score = findTrails(map, x + 1, y, score);
        }
        if(y > 0 && map[x][y-1] == next) {
            score = findTrails(map, x, y - 1, score);
        }
        if(y+1 < map.length && map[x][y+1] == next) {
            score = findTrails(map, x, y + 1, score);
        }
        return score;
    }


}
