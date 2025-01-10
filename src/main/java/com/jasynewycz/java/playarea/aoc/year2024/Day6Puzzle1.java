package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day6Puzzle1 {


    public static void main(String[] args) {


        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day6puzzle1testdata.txt")).toList().toArray(new String[0]);
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
                if (data[x][y] != 'X') {
                    data[x][y] = 'X';
                    total++;
                }
                
                switch (direction) {
                    case 0:
                        if (x > 0 && data[x-1][y] == '#') {
                            direction = turnRight(direction);
                        } else {
                            x--;
                        }
                        break;
                    case 1:
                        if (y < data[x].length-1 && data[x][y+1] == '#') {
                            direction = turnRight(direction);
                        } else {
                            y++;
                        }
                        break;
                    case 2:
                        if (x < data.length-1 && data[x+1][y] == '#') {
                            direction = turnRight(direction);
                        } else {
                            x++;
                        }
                        break;
                    case 3:
                        if (y > 0 && data[x][y-1] == '#') {
                            direction = turnRight(direction);
                        } else {
                            y--;
                        }
                        break;
                }
                
            }
            
            printData(data);

            System.out.println("Total: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
