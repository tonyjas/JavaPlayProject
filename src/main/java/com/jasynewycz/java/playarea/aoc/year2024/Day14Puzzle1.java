package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day14Puzzle1 {


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

            for (String line: lines) {

                int startX = Integer.parseInt(line.substring(line.indexOf("p=")+2, line.indexOf(",")));
                int startY = Integer.parseInt(line.substring(line.indexOf(",")+1, line.indexOf(" ")));

                String temp = line.substring(line.indexOf("v="));
                int offsetX = Integer.parseInt(temp.substring(temp.indexOf("v=")+2, temp.indexOf(",")));
                int offsetY = Integer.parseInt(temp.substring(temp.indexOf(",")+1));

                int posX = (startX + (iterations * offsetX)) % width;
                int posY = (startY + (iterations * offsetY)) % height;

                if(posX < 0) {
                    posX += width;
                }
                if(posY < 0) {
                    posY += height;
                }


                if(posX < (width-1) / 2) {
                    if(posY < (height-1) /2) {
                        q1++;
                    }
                    if(posY > (height-1) / 2) {
                        q2++;
                    }
                }
                if(posX > (width-1) / 2) {
                    if(posY < (height-1) /2) {
                        q3++;
                    }
                    if(posY > (height-1) / 2) {
                        q4++;
                    }
                }
            }

            total = ((long) q1 * q2 * q3 * q4);


            System.out.println("Result: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
