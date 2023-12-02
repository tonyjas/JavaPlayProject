package com.jasynewycz.java.playarea.aoc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Day1Puzzle2 {

    public static void main(String[] args) {

        try {

            long total = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day1puzzle1mytestdata.txt"))
//            long total = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/cornercases.txt"))
                    .mapToInt(Day1Puzzle2::getFirstPlusLast)
                    .sum();

            System.out.println("Total is: " + total);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getFirstPlusLast(String s) {
        System.out.println("first/last for: " + s + "\t\t" + findFirst(s) + "\t\t" + findLast(s));
        return Integer.parseInt("" + findFirst(s) + findLast(s));
    }

    private static int findFirst(String s) {

        int lowestStringIndex = s.length();
        String lowestStringValue = null;

        for(String numText : TEXT_DIGITS) {

            int index = s.indexOf(numText);
            if(index != -1 && index < lowestStringIndex) {
                lowestStringIndex = index;
                lowestStringValue = numText;
            }
        }

        for(int x = 0; x < lowestStringIndex; x++) {
            if(s.charAt(x) < 58 && s.charAt(x) > 47 ) {
                return s.charAt(x) - '0';
            }
        }
        return textToIntMap.get(lowestStringValue);
    }

    private static int findLast(String s) {

        int highestStringIndex = 0;
        String highestStringValue = null;

        for(String numText : TEXT_DIGITS) {

            int index = s.lastIndexOf(numText);
            if(index != -1 && index > highestStringIndex) {
                highestStringIndex = index;
                highestStringValue = numText;
            }
        }

        for(int x = s.length()-1; x >= highestStringIndex; x--) {
            if(s.charAt(x) < 58 && s.charAt(x) > 47 ) {
                return s.charAt(x) - '0';
            }
        }
        return textToIntMap.get(highestStringValue);
    }



    private static final String[] TEXT_DIGITS = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    private static final Map<String, Integer> textToIntMap = Map.ofEntries(
            Map.entry(TEXT_DIGITS[0], 1),
            Map.entry(TEXT_DIGITS[1], 2),
            Map.entry(TEXT_DIGITS[2], 3),
            Map.entry(TEXT_DIGITS[3], 4),
            Map.entry(TEXT_DIGITS[4], 5),
            Map.entry(TEXT_DIGITS[5], 6),
            Map.entry(TEXT_DIGITS[6], 7),
            Map.entry(TEXT_DIGITS[7], 8),
            Map.entry(TEXT_DIGITS[8], 9)
            );

}
