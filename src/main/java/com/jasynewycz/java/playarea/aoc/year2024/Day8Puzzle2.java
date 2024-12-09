package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8Puzzle2 {


    public static class Point {

        int x = 0;
        int y = 0;

        Point(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    public static void main(String[] args) {


        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day8puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            char[][] antiNodes = new char[lines.length][lines[0].length()];

            Map<Character, List<Day8Puzzle1.Point>> nodes = new HashMap<>();

            // populate map
            int x = 0;
            for (String line: lines) {

                for (int y = 0; y < line.length(); y++) {

                    char current = line.charAt(y);

                    if(current != '.') {

                        if(nodes.containsKey(current)) {
                            nodes.get(current).add(new Day8Puzzle1.Point(x, y));
                        } else {
                            List<Day8Puzzle1.Point> points = new ArrayList<>();
                            points.add(new Day8Puzzle1.Point(x, y));
                            nodes.put(current, points);
                        }
                    }
                }
                x++;
            }

            // populate antinodes array
            for (char key :nodes.keySet()) {

                List<Day8Puzzle1.Point> points = nodes.get(key);
                if(points.size() > 1) {
                    for (int i = 0; i < points.size(); i++) {
                        for (int j = 0; j < points.size(); j++) {
                            if(i == j) {
                                continue;
                            }
                            Day8Puzzle1.Point p1 = points.get(i);
                            Day8Puzzle1.Point p2 = points.get(j);
                            int xDiff = p1.x - p2.x;
                            int yDiff = p1.y - p2.y;
                            int multiplier = 0;

                            while (p1.x+(multiplier*xDiff) >= 0 && p1.x+(multiplier*xDiff) < antiNodes.length &&
                                    p1.y+(multiplier*yDiff) >= 0 && p1.y+(multiplier*yDiff) < antiNodes[0].length) {
                                antiNodes[p1.x + (multiplier*xDiff)][p1.y + (multiplier*yDiff)] = '#';
                                multiplier++;
                            }
                            multiplier = 0;
                            while (p2.x-(multiplier*xDiff) >= 0 && p2.x-(multiplier*xDiff) < antiNodes.length &&
                                    p2.y-(multiplier*yDiff) >= 0 && p2.y-(multiplier*yDiff) < antiNodes[0].length){
                                antiNodes[p2.x - (multiplier*xDiff)][p2.y - (multiplier*yDiff)] = '#';
                                multiplier++;
                            }
                        }
                    }
                }
            }

            // count antiNodes (could all be done in 1 step)
            for (int i = 0; i < antiNodes.length; i++) {
                for (int j = 0; j < antiNodes[i].length; j++) {

                    if(antiNodes[i][j] == '#') {
                        total++;
                    }
                }
            }

            System.out.println("Total: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
