package com.jasynewycz.java.playarea.aoc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day1Puzzle1 {

    public static void main(String[] args) {

        try {

            long total = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day1puzzle1mytestdata.txt"))
                    .mapToInt(Day1Puzzle1::getFirstPlusLast)
                    .sum();

            System.out.println("Total is: " + total);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getFirstPlusLast(String s) {
        return findFirst(s) + findLast(s);
    }

    private static int findFirst(String s) {

        for(int x = 0; x < s.length(); x++) {
            if(s.charAt(x) < 58 && s.charAt(x) > 47 ) {
                return s.charAt(x) - '0';
            }
        }
        return -1;
    }

    private static int findLast(String s) {

        for(int x = s.length()-1; x >= 0; x--) {
            if(s.charAt(x) < 58 && s.charAt(x) > 47 ) {
                return s.charAt(x) - '0';
            }
        }
        return -1;
    }

}
