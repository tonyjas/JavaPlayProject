package com.jasynewycz.java.playarea.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day6puzzle2 {


    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day6puzzle1realdata.txt"));

        long time = Long.parseLong(lines.get(0).substring(5).replaceAll(" ", ""));
        long distance = Long.parseLong(lines.get(1).substring(9).replaceAll(" ", ""));

        System.out.println("times: " + time);
        System.out.println("distances: " + distance);


        int winningCounter = 0;

        for(long y = 1; y < time; y++) {
            long speed = y;
            long timeLeft = time - y;
            long distanceTravelled = speed * timeLeft;
            if(distanceTravelled > distance) {
                winningCounter++;
            }
        }
        System.out.println(winningCounter);

    }

}
