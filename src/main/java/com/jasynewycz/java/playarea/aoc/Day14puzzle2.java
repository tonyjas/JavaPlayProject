package com.jasynewycz.java.playarea.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day14puzzle2 {


    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day14puzzle1realdata.txt"));

        // transpose the data to enable String compare
        char[][] data = new char[lines.size()][lines.get(0).length()];

        for(int x = 0; x < data.length; x++) {
            for(int y = 0; y < data[x].length; y++) {
                data[x][y] = lines.get(x).charAt(y);
            }
        }

        long total = 0;

        //int cycles = 1_000_000_000;

        int cycles = 1000;


        while (cycles-- > 0) {

            boolean changed = false;
            int swapCount = 0;

            if(cycles % 1_000 == 0) {
                System.out.println("" + cycles );
            }

            for (int y = 0; y < data[0].length; y++) {
                for (int x = 0; x < data.length; x++) {
                    if (data[x][y] == 'O') {
                        int z = x;
                        while (z > 0 && data[z - 1][y] == '.') {
                            z--;
                        }
                        swap(data, z, y, x, y);
                        changed = true;
                        swapCount++;
                    }
                }
            }


            for (int x = 0; x < data.length; x++) {
                for (int y = 0; y < data[x].length; y++) {
                    if (data[x][y] == 'O') {
                        int z = y;
                        while (z > 0 && data[x][z - 1] == '.') {
                            z--;
                        }
                        swap(data, x, z, x, y);
                        changed = true;
                        swapCount++;
                    }
                }
            }

            for (int y = 0; y < data[0].length; y++) {
                for (int x = data.length - 1; x >= 0; x--) {
                    if (data[x][y] == 'O') {
                        int z = x;
                        while (z < data[0].length - 1 && data[z + 1][y] == '.') {
                            z++;
                        }
                        swap(data, z, y, x, y);
                        changed = true;
                        swapCount++;
                    }
                }
            }

            for (int x = 0; x < data.length; x++) {
                for (int y = data[x].length - 1; y >= 0; y--) {
                    if (data[x][y] == 'O') {
                        int z = y;
                        while (z < data[x].length - 1 && data[x][z + 1] == '.') {
                            z++;
                        }
                        swap(data, x, z, x, y);
                        changed = true;
                        swapCount++;
                    }
                }
            }

            int tempTotal = 0;
            for (int y = 0; y < data[0].length; y++) {
                for (int x = 0; x < data.length; x++) {
                    if (data[x][y] == 'O') {

                        tempTotal += data.length - x;
                    }
                }
            }
            System.out.println("Index : " + cycles + "\t\tSwaps: " + swapCount + "\t\t" + tempTotal);

            if(!changed) {
                // calculate total and output
                break;
            }
        }


        int offset = (1_000_000_000 - 91) % 63;


        System.out.println("Pattern repeats every 63 cycles, the cycle starts after 91 initial cycles.");
        System.out.println("offset in the pattern is: " + offset + " giving a result of: " + 118747);
    }

    private static void swap(char[][] array, int x, int y, int oldX, int oldY) {

        char temp = array[x][y];
        array[x][y] = array[oldX][oldY];
        array[oldX][oldY] = temp;
    }

}
