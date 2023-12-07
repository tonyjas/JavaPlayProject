package com.jasynewycz.java.playarea.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day6puzzle1 {


    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day6puzzle1realdata.txt"));

        int[] times = Arrays.stream(lines.get(0).substring(5).trim().split(" "))
                .filter(s -> !s.isBlank())
                .mapToInt(Integer::parseInt).
                toArray();

        int[] distances = Arrays.stream(lines.get(1).substring(9).trim().split(" "))
                .filter(s -> !s.isBlank())
                .mapToInt(Integer::parseInt).
                toArray();

        System.out.println("times: " + Arrays.toString(times));
        System.out.println("distances: " + Arrays.toString(distances));

        long result = 1L;

        for(int x = 0; x < times.length; x++) {
            int time = times[x];
            int distance = distances[x];

            int winningCounter = 0;

            for(int y = 1; y < time; y++) {
                int speed = y;
                int timeLeft = time - y;
                int distanceTravelled = speed * timeLeft;
                if(distanceTravelled > distance) {
                    winningCounter++;
                }
            }
            System.out.println(winningCounter);
            result *= winningCounter;
        }

        System.out.println(result);
    }
}
