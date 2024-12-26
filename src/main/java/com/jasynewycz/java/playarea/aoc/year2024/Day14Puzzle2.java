package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day14Puzzle2 {



    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day14puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            int width = 101;
            int height = 103;


            int q1 = 0;
            int q2 = 0;
            int q3 = 0;
            int q4 = 0;


            int iterations = 100;

            for (int x = 1; x < 10000; x++) {

                iterations = x;
                int[][] data = new int[width][height];

                boolean print = false;

                for (String line : lines) {

                    int startX = Integer.parseInt(line.substring(line.indexOf("p=") + 2, line.indexOf(",")));
                    int startY = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(" ")));

                    String temp = line.substring(line.indexOf("v="));
                    int offsetX = Integer.parseInt(temp.substring(temp.indexOf("v=") + 2, temp.indexOf(",")));
                    int offsetY = Integer.parseInt(temp.substring(temp.indexOf(",") + 1));

                    int posX = (startX + (iterations * offsetX)) % width;
                    int posY = (startY + (iterations * offsetY)) % height;

                    if (posX < 0) {
                        posX += width;
                    }
                    if (posY < 0) {
                        posY += height;
                    }

                    data[posX][posY]++;

                    if(posY > 3 && posY < height-2 && data[posX][posY-1] != 0 && data[posX][posY-2] != 0 && data[posX][posY-3] != 0 && data[posX][posY+1] != 0 && data[posX][posY+2] != 0) {
                        print = true;
                    }
                }

                if (print) {
                    System.out.println("Iteration: " + iterations);
                    printData(data);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printData(int[][] data) {

        for(int x = 0; x < data.length; x++) {
            for(int y = 0; y < data[x].length; y++) {
                if(data[x][y] != 0) {
                    System.out.print(data[x][y]);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println("*********************************************");
    }
}
