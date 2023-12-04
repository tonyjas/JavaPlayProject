package com.jasynewycz.java.playarea.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day4puzzle1 {

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day4puzzle1realdata.txt"));

        Set<String> set = new HashSet<>();

        long total = 0;

        for(String line : lines) {
            int ticketTotal = 0;

            String[] parts = line.split("[:|]");
            String[] results = parts[1].split(" ");
            for(String result : results) {
                if(!result.isEmpty()) {
                    set.add(result);
                }
            }
            String[] ticketNumbers = parts[2].split(" ");

            for(String number : ticketNumbers) {
                if(set.contains(number)) {
                    if(ticketTotal == 0) {
                        ticketTotal = 1;
                    } else {
                        ticketTotal *= 2;
                    }
                }
            }
            set.clear();
            total += ticketTotal;
        }

        System.out.println(total);
    }

}
