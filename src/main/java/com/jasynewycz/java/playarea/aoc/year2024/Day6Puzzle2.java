package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Day6Puzzle2 {

    static Set<String> results = new HashSet<>();

    public static void main(String[] args) {


        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day6puzzle1testdata.txt")).toList().toArray(new String[0]);
//            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day6playdata.txt")).toList().toArray(new String[0]);
            long total = 0;
            int x=0, y =0;
            int direction = 0;


            char[][] data = new char[lines.length][];

            int i = 0;
            for (String line: lines) {
                data[i] = line.toCharArray();
                if (line.indexOf('^') != -1) {
                    x = i;
                    y = line.indexOf('^');
                }
                i++;
            }

            while (x < data.length && x >= 0 && y < data[0].length && y >= 0) {

                System.out.println("(" + x + "," + y + "):" + direction + "\ttotal: " + total);

                switch (direction) {
                    case 0:
                        data[x][y] = '^';
                        if (x > 0 && data[x-1][y] == '#') {
                            direction = turnRight(direction);
                        } else {
                            if(x > 0) {
                                char temp = data[x - 1][y];
                                data[x - 1][y] = '#';
                                if (loopExists(x, y, direction, data)) {
                                    results.add("x" + (x-1) + "y"+ y);
                                    total++;
                                }
                                data[x - 1][y] = temp;
                            }
                            x--;
                        }
                        break;
                    case 1:
                        data[x][y] = '>';
                        if (y < data[x].length-1 && data[x][y+1] == '#') {
                            direction = turnRight(direction);
                        } else {
                            if(y < data[x].length-1 ) {
                                char temp = data[x][y + 1];
                                data[x][y + 1] = '#';
                                if (loopExists(x, y, direction, data)) {
                                    results.add("x" + (x) + "y"+ (y+1));
                                    total++;
                                }
                                data[x][y + 1] = temp;
                            }
                            y++;
                        }
                        break;
                    case 2:
                        data[x][y] = 'v';
                        if (x < data.length-1 && data[x+1][y] == '#') {
                            direction = turnRight(direction);
                        } else {
                            if(x < data.length-1 ) {
                                char temp = data[x + 1][y];
                                data[x + 1][y] = '#';
                                if (loopExists(x, y, direction, data)) {
                                    results.add("x" + (x+1) + "y" + y);
                                    total++;
                                }
                                data[x + 1][y] = temp;
                            }
                            x++;
                        }
                        break;
                    case 3:
                        data[x][y] = '<';
                        if (y > 0 && data[x][y-1] == '#') {
                            direction = turnRight(direction);
                        } else {
                            if(y > 0) {
                                char temp = data[x][y - 1];
                                data[x][y - 1] = '#';
                                if (loopExists(x, y, direction, data)) {
                                    results.add("x" + (x) + "y"+(y-1));
                                    total++;
                                }
                                data[x][y - 1] = temp;
                            }
                            y--;
                        }
                        break;
                }
            }

            printData(data);

            System.out.println("Total1: " + total);
            System.out.println("Total2: " + results.size());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

// 1830 too low

    static boolean loopExists(int x, int y, int direction, char[][] data) {


        Set<String> localSet = new HashSet<>();

        while (x < data.length && x >= 0 && y < data[0].length && y >= 0) {

            if(localSet.contains("x" + x + "y"+ y + "d"+direction)) {
                return true;
            } else {
                localSet.add("x" + x + "y"+ y + "d"+direction);
            }

            switch (direction) {
                case 0:
                    //data[x][y] = '^';
                    if (x > 0 && data[x-1][y] == '#') {
                        direction = turnRight(direction);
                    } else {
                        x--;
                    }
                    break;
                case 1:
                    //data[x][y] = '>';
                    if (y < data[x].length-1 && data[x][y+1] == '#') {
                        direction = turnRight(direction);
                    } else {
                        y++;
                    }
                    break;
                case 2:
                    //data[x][y] = 'v';
                    if (x < data.length-1 && data[x+1][y] == '#') {
                        direction = turnRight(direction);
                    } else {
                        x++;
                    }
                    break;
                case 3:
                    //data[x][y] = '<';
                    if (y > 0 && data[x][y-1] == '#') {
                        direction = turnRight(direction);
                    } else {
                        y--;
                    }
                    break;
            }

        }
        return false;
    }


    private static int turnRight(int direction) {
        if(direction < 3) {
            return ++direction;
        }
        return 0;
    }

    private static void printData(char[][] data) {
        int i;
        for (i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j]);
            }
            System.out.println();
        }
    }
}