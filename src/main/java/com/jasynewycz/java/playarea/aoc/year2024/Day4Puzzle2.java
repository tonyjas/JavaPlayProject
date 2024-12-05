package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day4Puzzle2 {

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day4puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            char[][] data = new char[lines.length][lines[0].length()];

            int x = 0;
            for(String line: lines) {

                for (int y = 0; y < line.length(); y++) {

                    data[x][y] = line.charAt(y);
                }
                x++;

            }

            // looking for 'A' not on the edges of the data
            for(int i = 1; i < data.length-1; i++) {
                for(int j = 1; j < data[i].length-1; j++) {

                    if(data[i][j] == 'A') {
                        total += findMas(data, i, j);
                    }
                }
            }


            System.out.println("Total: " + total);

        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static int findMas(char[][] data, int x, int y) {

        if(data[x-1][y-1] == 'M' && data[x-1][y+1] == 'M' && data[x+1][y-1] == 'S' && data[x+1][y+1] == 'S') return 1;
        if(data[x-1][y-1] == 'M' && data[x-1][y+1] == 'S' && data[x+1][y-1] == 'M' && data[x+1][y+1] == 'S') return 1;
        if(data[x-1][y-1] == 'S' && data[x-1][y+1] == 'S' && data[x+1][y-1] == 'M' && data[x+1][y+1] == 'M') return 1;
        if(data[x-1][y-1] == 'S' && data[x-1][y+1] == 'M' && data[x+1][y-1] == 'S' && data[x+1][y+1] == 'M') return 1;

        return 0;
    }
}
