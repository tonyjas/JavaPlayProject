package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day16Puzzle2 {

    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;


    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x=x;
            this.y=y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static class MazePath {
        List<Point> points;
        long score;

        public MazePath(List<Point> points, long score) {
            this.points=points;
            this.score=score;
        }
    }

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
            List<Point> path = new ArrayList<>();
            List<MazePath> paths = new ArrayList<>();

            char[][] data = new char[lines.length][lines[0].length()];

            for (int x = 0; x < data.length; x++) {
                data[x] = lines[x].toCharArray();
            }

            List<Long> scores = new ArrayList<>();
            getPath(data, scores, visited, path, paths,0, startX, startY, direction);



            long lowScore = scores.get(0);

            for (long score : scores) {

                if (score < lowScore) {
                    lowScore = score;
                }
            }

            Set<Point> uniquePoints = new HashSet<>();

            // count points:
            for (MazePath mPath: paths) {

                if (mPath.score == lowScore) {
                    uniquePoints.addAll(mPath.points);
                }
            }

            System.out.println("Result: " + uniquePoints.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getPath(char[][] data, List<Long> scores, Map<String, Long> visited, List<Point> p, List<MazePath> mazePath, long score, int x, int y, int direction) {

        if(visited.get("x: " + x + "y: " + y) != null && visited.get("x: " + x + "y: " + y) < score) {
            return;
        }
        visited.put("x: " + x + "y: " + y, score);

        List<Point> path = new ArrayList<>();
        path.addAll(p);

        switch (direction) {
            case NORTH:
                while (data[x][y] != '#') {
                    path.add(new Point(x, y));
                    if(data[x][y] == 'E') {
                        scores.add(score);
                        mazePath.add(new MazePath(path, score));
                    }
                    if(data[x][y-1] != '#') {
                        getPath(data, scores, visited, path, mazePath, score+1001, x, y-1, WEST);
                    }
                    if(data[x][y+1] != '#') {
                        getPath(data, scores, visited, path, mazePath, score+1001, x, y+1, EAST);
                    }
                    score++;
                    x--;
                }
                break;
            case EAST:
                while (data[x][y] != '#') {
                    path.add(new Point(x, y));
                    if(data[x][y] == 'E') {
                        scores.add(score);
                        mazePath.add(new MazePath(path, score));
                    }
                    if(data[x-1][y] != '#') {
                        getPath(data, scores, visited, path, mazePath, score+1001, x-1, y, NORTH);
                    }
                    if(data[x+1][y] != '#') {
                        getPath(data, scores, visited, path, mazePath, score+1001, x+1, y, SOUTH);
                    }
                    score++;
                    y++;
                }
                break;
            case SOUTH:
                while (data[x][y] != '#') {
                    path.add(new Point(x, y));
                    if(data[x][y] == 'E') {
                        scores.add(score);
                        mazePath.add(new MazePath(path, score));
                    }
                    if(data[x][y-1] != '#') {
                        getPath(data, scores, visited, path, mazePath, score+1001, x, y-1, WEST);
                    }
                    if(data[x][y+1] != '#') {
                        getPath(data, scores, visited, path, mazePath, score+1001, x, y+1, EAST);
                    }
                    score++;
                    x++;
                }
                break;
            case WEST:
                while (data[x][y] != '#') {
                    path.add(new Point(x, y));
                    if(data[x][y] == 'E') {
                        scores.add(score);
                        mazePath.add(new MazePath(path, score));
                    }
                    if(data[x-1][y] != '#') {
                        getPath(data, scores, visited, path, mazePath, score+1001, x-1, y, NORTH);
                    }
                    if(data[x+1][y] != '#') {
                        getPath(data, scores, visited, path, mazePath, score+1001, x+1, y, SOUTH);
                    }
                    score++;
                    y--;
                }
                break;
        }
    }
}
