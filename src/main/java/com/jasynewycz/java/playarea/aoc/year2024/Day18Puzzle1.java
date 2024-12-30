package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day18Puzzle1 {

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day18puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            char[][] data = new char[71][71];

            int x = 0;
            for (String line: lines) {
                String[] temp = line.split(",");
                data[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])] = '#';
                x++;
                if (x == 2967) {
                    System.out.println(Integer.parseInt(temp[0]) + "," + Integer.parseInt(temp[1]));
                    break;
                }
            }

            printData(data);
            total = findPath(data, new HashSet<String>(), new HashMap<String, Integer>(), 0, 0);


            System.out.println("Result: "  + (total-1));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int findPath(char[][] data, Set<String> v, HashMap<String, Integer> scores, int x, int y) {

        String key = "c:" + x + "r:" + y;

        if(v.contains(key) || data[y][x] == '#') {
            return Integer.MAX_VALUE;
        }


        Set<String> visited = new HashSet<>(v);
        visited.add(key);

        if(scores.containsKey(key)) {
            if(scores.get(key) > visited.size()) {
                scores.put(key, visited.size());
            } else {
                return Integer.MAX_VALUE;
            }
        } else {
            scores.put(key, visited.size());
        }


        if (y == data.length-1 && x == data[x].length-1) {
//            System.out.println(visited);
            return visited.size();
        }

        int score = Integer.MAX_VALUE;

        if (x != 0) {
            score = Math.min(findPath(data, visited, scores, x-1, y), score);
        }
        if (x != data.length-1) {
            score = Math.min(findPath(data, visited, scores, x+1, y), score);
        }
        if (y != 0) {
            score = Math.min(findPath(data, visited, scores, x, y-1), score);
        }
        if (y != data[x].length-1) {
            score = Math.min(findPath(data, visited, scores, x, y+1), score);
        }

        return score;
    }

    public static final void printData(char[][] data) {
        for (int x = 0 ; x < data.length; x++) {
            for (int y = 0; y < data[x].length; y++) {
                System.out.print(data[x][y]);
            }
            System.out.println();
        }

    }
}
