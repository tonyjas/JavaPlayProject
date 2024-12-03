package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Puzzle1 {

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day3puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;
            Pattern pattern = Pattern.compile("mul\\(\\d*,\\d*\\)");

            for(String line: lines) {
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    String text = matcher.group();
                    int first = Integer.parseInt(text.substring(text.indexOf("(") + 1, text.indexOf(",")));
                    int second = Integer.parseInt(text.substring(text.indexOf(",") + 1, text.indexOf(")")));

                    total += ((long) first * second);

                    System.out.println("* " + text + "\t" + first + "*" + second);
                }
            }
            System.out.println("Total: " + total);

        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
