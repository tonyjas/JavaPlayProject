package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day4Puzzle1 {

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

            for(int i = 0; i < data.length; i++) {
                for(int j = 0; j < data[i].length; j++) {

                    if(data[i][j] == 'X') {
                        total += findXmas(data, i, j);
                    }
                }
            }


            System.out.println("Total: " + total);

        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static int findXmas(char[][] data, int x, int y) {

        int maxX = data.length;
        int maxY = data[0].length;

        int finds = 0;
        // scan left and right
        if(x < maxX-3 && data[x+1][y] == 'M' && data[x+2][y] == 'A' && data[x+3][y] == 'S') finds++;
        if(x > 2 && data[x-1][y] == 'M' && data[x-2][y] == 'A' && data[x-3][y] == 'S') finds++;

        // scan up and down
        if(y < maxY-3 && data[x][y+1] == 'M' && data[x][y+2] == 'A' && data[x][y+3] == 'S') finds++;
        if(y > 2 && data[x][y-1] == 'M' && data[x][y-2] == 'A' && data[x][y-3] == 'S') finds++;


        // scan diag bottom left to top right
        if(x < maxX-3 && y < maxY-3 && data[x+1][y+1] == 'M' && data[x+2][y+2] == 'A' && data[x+3][y+3] == 'S') finds++;
        if(x > 2 && y > 2 && data[x-1][y-1] == 'M' && data[x-2][y-2] == 'A' && data[x-3][y-3] == 'S') finds++;


        // scan diag top left to bottom right
        if(x < maxX-3 && y > 2 && data[x+1][y-1] == 'M' && data[x+2][y-2] == 'A' && data[x+3][y-3] == 'S') finds++;
        if(x > 2 && y < maxY-3 && data[x-1][y+1] == 'M' && data[x-2][y+2] == 'A' && data[x-3][y+3] == 'S') finds++;

        return finds;
    }
}