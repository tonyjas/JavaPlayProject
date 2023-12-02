package com.jasynewycz.java.playarea.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day2Puzzle1 {

    record Game(int id, int red, int green, int blue) {


    }

    public static void main(String[] args) {

        try {
//            long total = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day2puzzle1testdata.txt"))
            long total = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day2puzzle1realdata.txt"))
                    .map(Day2Puzzle1::parseLine)
                    .filter(Day2Puzzle1::isValid)
                    .mapToInt(Game::id)
                    .sum();

            System.out.println(total);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static final int MAX_RED = 12;
    private static final int MAX_GREEN = 13;
    private static final int MAX_BLUE = 14;


    private static boolean isValid(Game game) {


        if (game.red() > MAX_RED) {
            return false;
        }
        if (game.green() > MAX_GREEN) {
            return false;
        }
        if (game.blue() > MAX_BLUE) {
            return false;
        }
        return true;
    }


    private static Game parseLine(String s) {
        String[] splitString = s.split("[;:]");
        int id, maxRed=0, maxGreen=0, maxBlue=0;

        id = Integer.parseInt(splitString[0].split("[ ]")[1]);


        for(int x = 1 ; x < splitString.length; x++) {

            String[] splitGame = splitString[x].split("[ ,;]");
            for(int y = 0; y < splitGame.length; y+=3) {

                int currentValue = Integer.parseInt(splitGame[y+1]);
                String currentColor = splitGame[y+2];

                switch(currentColor) {
                    case "red" -> {
                        if (currentValue > maxRed) {
                            maxRed = currentValue;
                        }
                    }
                    case "green" -> {
                        if (currentValue > maxGreen) {
                            maxGreen = currentValue;
                        }
                    }
                    case "blue" -> {
                        if (currentValue > maxBlue) {
                            maxBlue = currentValue;
                        }
                    }

                }
            }
        }

        System.out.println("id: " + id + "\tR: " + maxRed +
                "\tG: " + maxGreen +
                "\tB: " + maxBlue
        );
        return new Game(id, maxRed, maxGreen, maxBlue);
    }
}
