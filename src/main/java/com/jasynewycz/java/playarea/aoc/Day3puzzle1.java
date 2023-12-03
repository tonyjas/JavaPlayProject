package com.jasynewycz.java.playarea.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day3puzzle1 {

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(
                Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day3puzzle1realdata.txt"));
        System.out.println(getSum(lines));

    }


    public static long getSum(List<String> lines) {

        long total = 0;

        String prev = "";
        String current = "";
        String next = "";

        for(int x = 0; x < lines.size(); x++) {
            if(x != 0) {
                prev = lines.get(x-1);
            }
            current = lines.get(x);
            if(x == lines.size()-1) {
                next = "";
            } else {
                next = lines.get(x+1);
            }

            boolean adjacent = false;

            for(int y = 0; y < current.length(); y++) {

                if(current.charAt(y) >= '0' && current.charAt(y) <= '9') {
                    // consume number
                    int start = y;
                    int end = y;
                    while (y < current.length()) {
                        if(current.charAt(y) >= '0' && current.charAt(y) <= '9') {
                            y++;
                        } else {
                            break;
                        }
                    }
                    String number = current.substring(start, y);
                    if(isAdjacent(start, y, prev, current, next)) {
                        total += Integer.parseInt(number);
                    }
                }
            }
        }
        return total;
    }

    private static boolean isAdjacent(int start, int end, String prev, String current, String next) {

        for(int x = Math.max(start-1, 0); x < Math.min(end+1, current.length()); x++) {
            if(!prev.isEmpty() && prev.charAt(x) != '.' && (prev.charAt(x) < '0' || prev.charAt(x) > '9')) {
                return true;
            }
            if(current.charAt(x) != '.' && (current.charAt(x) < '0' || current.charAt(x) > '9')) {
                return true;
            }
            if(!next.isEmpty() && next.charAt(x) != '.' && (next.charAt(x) < '0' || next.charAt(x) > '9')) {
                return true;
            }
        }
        return false;
    }
}
