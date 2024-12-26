package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day15Puzzle2 {

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day15puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            char[][] data = new char[lines.length][lines[0].length()*2];
            int posX = 0;
            int posY = 0;

            int x = 0;
            String line;
            do {
                line = lines[x];
                if(line.contains("@")) {
                    posX = x;
                    posY = line.indexOf('@')*2;
                }
                data[x++] = expandChars(line.toCharArray());
            } while(line.startsWith("#"));

            printData(data);

            for (; x < lines.length; x++) {
                if(lines[x].isBlank()) {
                    continue;
                }
                // do the work
                for (int y = 0; y < lines[x].length(); y++) {
                    char direction = lines[x].charAt(y);
                    List<Point> boxes = new ArrayList<>();

                    /*
                    need to use a set here to avoid scenario such as below pushing ^
                    top box would get processed 2 times. Use list to accumulate to maintain insertion order
                    and then add to set as we process in reverse order.
                     []
                    [][]
                     []
                     @
                     */
                    Set<Point> processed = new HashSet<>();

                    switch(direction) {
                        case '^':
                            if (scanUpForBoxes(boxes, new Point(posX, posY, data[posX][posY]), data)) {

                                for (int z = boxes.size()-1; z >= 0; z--) {
                                    if (processed.add(boxes.get(z))) {
                                        Point current = boxes.get(z);
                                        data[current.x - 1][current.y] = current.c;
                                        data[current.x][current.y] = '.';
                                    }
                                }
                            }
                            if (data[posX - 1][posY] == '.') {
                                data[posX][posY] = '.';
                                data[posX - 1][posY] = '@';
                                posX--;
                            }
                            break;
                        case 'v':
                            if (scanDownForBoxes(boxes, new Point(posX, posY, data[posX][posY]), data)) {

                                for (int z = boxes.size()-1; z >= 0; z--) {
                                    if (processed.add(boxes.get(z))) {
                                        Point current = boxes.get(z);
                                        data[current.x + 1][current.y] = current.c;
                                        data[current.x][current.y] = '.';
                                    }
                                }
                            }
                            if (data[posX + 1][posY] == '.') {
                                data[posX][posY] = '.';
                                data[posX + 1][posY] = '@';
                                posX++;
                            }
                            break;
                        case '>':
                            if (scanRightForBoxes(boxes, new Point(posX, posY, data[posX][posY]), data)) {

                                for (int z = boxes.size()-1; z >= 0; z--) {
                                    Point current = boxes.get(z);
                                    data[current.x][current.y+1] = current.c;
                                    data[current.x][current.y] = '.';
                                }
                            }
                            if (data[posX][posY + 1] == '.') {
                                data[posX][posY] = '.';
                                data[posX][posY + 1] = '@';
                                posY++;
                            }
                            break;
                        case '<':
                            if (scanLeftForBoxes(boxes, new Point(posX, posY, data[posX][posY]), data)) {

                                for (int z = boxes.size()-1; z >= 0; z--) {
                                    Point current = boxes.get(z);
                                    data[current.x][current.y-1] = current.c;
                                    data[current.x][current.y] = '.';
                                }
                            }
                            if (data[posX][posY - 1] == '.') {
                                data[posX][posY] = '.';
                                data[posX][posY - 1] = '@';
                                posY--;
                            }
                            break;
                    }
                }
            }

            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {

                    if (data[i][j] == '[') {
                        total += (j + (i * 100L));
                    }
                }
            }

            printData(data);

            System.out.println("Result: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static boolean scanUpForBoxes(List<Point> boxesToMove, Point p1, char[][] data) {

        if (data[p1.x-1][p1.y] == '#') {
            return false;
        }
        if (data[p1.x-1][p1.y] == '[') {
            boxesToMove.add(new Point(p1.x-1, p1.y, '['));
            boxesToMove.add(new Point(p1.x-1, p1.y+1, ']'));
            boolean b1 = scanUpForBoxes(boxesToMove, new Point(p1.x-1, p1.y, '['), data);
            boolean b2 = scanUpForBoxes(boxesToMove, new Point(p1.x-1, p1.y+1, ']'), data);
            return (b1 && b2);
        } else if (data[p1.x-1][p1.y] == ']') {
            boxesToMove.add(new Point(p1.x-1, p1.y, ']'));
            boxesToMove.add(new Point(p1.x-1, p1.y-1, '['));
            boolean b1 = scanUpForBoxes(boxesToMove, new Point(p1.x-1, p1.y, ']'), data);
            boolean b2 = scanUpForBoxes(boxesToMove, new Point(p1.x-1, p1.y-1, '['), data);
            return (b1 && b2);
        }
        return true;
    }

    private static boolean scanDownForBoxes(List<Point> boxesToMove, Point p1, char[][] data) {

        if (data[p1.x+1][p1.y] == '#') {
            return false;
        }
        if (data[p1.x+1][p1.y] == '[') {
            boxesToMove.add(new Point(p1.x+1, p1.y, '['));
            boxesToMove.add(new Point(p1.x+1, p1.y+1, ']'));
            boolean b1 = scanDownForBoxes(boxesToMove, new Point(p1.x+1, p1.y, '['), data);
            boolean b2 = scanDownForBoxes(boxesToMove, new Point(p1.x+1, p1.y+1, ']'), data);
            return (b1 && b2);
        } else if (data[p1.x+1][p1.y] == ']') {
            boxesToMove.add(new Point(p1.x+1, p1.y, ']'));
            boxesToMove.add(new Point(p1.x+1, p1.y-1, '['));
            boolean b1 = scanDownForBoxes(boxesToMove, new Point(p1.x+1, p1.y, ']'), data);
            boolean b2 = scanDownForBoxes(boxesToMove, new Point(p1.x+1, p1.y-1, '['), data);
            return (b1 && b2);
        }
        return true;
    }


    private static boolean scanLeftForBoxes(List<Point> boxesToMove, Point p1, char[][] data) {

        if (data[p1.x][p1.y-1] == '#') {
            return false;
        }
        if (data[p1.x][p1.y-1] == ']') {
            boxesToMove.add(new Point(p1.x, p1.y-1, ']'));
            boxesToMove.add(new Point(p1.x, p1.y-2, '['));
            return scanLeftForBoxes(boxesToMove, new Point(p1.x, p1.y-2, '['), data);
        }
        return true;
    }

    private static boolean scanRightForBoxes(List<Point> boxesToMove, Point p1, char[][] data) {

        if (data[p1.x][p1.y+1] == '#') {
            return false;
        }
        if (data[p1.x][p1.y+1] == '[') {
            boxesToMove.add(new Point(p1.x, p1.y+1, '['));
            boxesToMove.add(new Point(p1.x, p1.y+2, ']'));
            return scanRightForBoxes(boxesToMove, new Point(p1.x, p1.y+2, ']'), data);
        }
        return true;
    }


    private static void printData(char[][] data) {

        for(int x = 0; x < data.length; x++) {
            if(data[x].length > 0 && data[x][0] == '#') {
                for (int y = 0; y < data[x].length; y++) {
                    if (data[x][y] != 0) {
                        System.out.print(data[x][y]);
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.println();
            }
        }
    }

    private static char[] expandChars(char[] input) {

        char[] output = new char[input.length*2];

        int x = 0;
        for (char next: input) {
            switch(next) {
                case '#':
                    output[x] = '#';
                    output[x+1] = '#';
                    break;
                case 'O':
                    output[x] = '[';
                    output[x+1] = ']';
                    break;
                case '.':
                    output[x] = '.';
                    output[x+1] = '.';
                    break;
                case '@':
                    output[x] = '@';
                    output[x+1] = '.';
                    break;
            }
            x += 2;
        }
        return output;
    }

    static class Point {
        int x;
        int y;
        char c;

        public Point(int x, int y, char c) {
            this.x=x;
            this.y=y;
            this.c=c;
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
}
