package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day16Puzzle1 {

    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;


    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day16puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            int startX = lines.length-2;
            int startY = 1;
            int endX = 1;
            int endY = lines[0].length()-2;
            int direction = EAST;
            Map<String, Long> visited = new HashMap<>();

            char[][] data = new char[lines.length][lines[0].length()];

            for (int x = 0; x < data.length; x++) {
                data[x] = lines[x].toCharArray();
            }

            List<Long> scores = new ArrayList<>();
            getPath(data, scores, visited, 0, startX, startY, direction);

            long lowScore = scores.get(0);

            for (long score : scores) {

                if (score < lowScore) {
                    lowScore = score;
                }
            }

            System.out.println("Result: " + lowScore);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getPath(char[][] data, List<Long> scores, Map<String, Long> visited, long score, int x, int y, int direction) {

        if(visited.get("x: " + x + "y: " + y) != null && visited.get("x: " + x + "y: " + y) < score) {
            return;
        }
        visited.put("x: " + x + "y: " + y, score);

        switch (direction) {
            case NORTH:
                while (data[x][y] != '#') {
                    if(data[x][y] == 'E') {
                        scores.add(score);
                    }
                    if(data[x][y-1] != '#') {
                        getPath(data, scores, visited, score+1001, x, y-1, WEST);
                    }
                    if(data[x][y+1] != '#') {
                        getPath(data, scores, visited, score+1001, x, y+1, EAST);
                    }
                    score++;
                    x--;
                }
                break;
            case EAST:
                while (data[x][y] != '#') {
                    if(data[x][y] == 'E') {
                        scores.add(score);
                    }
                    if(data[x-1][y] != '#') {
                        getPath(data, scores, visited, score+1001, x-1, y, NORTH);
                    }
                    if(data[x+1][y] != '#') {
                        getPath(data, scores, visited, score+1001, x+1, y, SOUTH);
                    }
                    score++;
                    y++;
                }
                break;
            case SOUTH:
                while (data[x][y] != '#') {
                    if(data[x][y] == 'E') {
                        scores.add(score);
                    }
                    if(data[x][y-1] != '#') {
                        getPath(data, scores, visited, score+1001, x, y-1, WEST);
                    }
                    if(data[x][y+1] != '#') {
                        getPath(data, scores, visited, score+1001, x, y+1, EAST);
                    }
                    score++;
                    x++;
                }
                break;
            case WEST:
                while (data[x][y] != '#') {
                    if(data[x][y] == 'E') {
                        scores.add(score);
                    }
                    if(data[x-1][y] != '#') {
                        getPath(data, scores, visited, score+1001, x-1, y, NORTH);
                    }
                    if(data[x+1][y] != '#') {
                        getPath(data, scores, visited, score+1001, x+1, y, SOUTH);
                    }
                    score++;
                    y--;
                }
                break;
        }
    }
}
