package com.jasynewycz.java.playarea.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day14puzzle1 {

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day14puzzle1realdata.txt"));

        // transpose the data to enable String compare
        char[][] data = new char[lines.get(0).length()][lines.size()];

        for(int x = 0; x < data.length; x++) {
            for(int y = 0; y < data[x].length; y++) {
                data[x][y] = lines.get(y).charAt(x);
            }
        }

        long total = 0;

        for(int x = 0; x < data.length; x++) {
            for(int y = 0; y < data[x].length; y++) {
                if(data[x][y] == 'O') {
                    int z = y;
                    while(z > 0 && data[x][z-1] == '.') {
                        swap(data, x, z-1, z);
                        z--;
                    }
                    total += data[x].length - z;
                }
            }
        }

        System.out.println(total);
    }

    private static void swap(char[][] array, int x, int y, int oldY) {

        char temp = array[x][y];
        array[x][y] = array[x][oldY];
        array[x][oldY] = temp;
    }
}
