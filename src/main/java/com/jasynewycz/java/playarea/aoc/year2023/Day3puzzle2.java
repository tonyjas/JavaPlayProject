package com.jasynewycz.java.playarea.aoc.year2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day3puzzle2 {

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(
                Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2023/day3puzzle1realdata.txt"));
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

                if(current.charAt(y) == '*') {

                    int[] adjacentNums = getAdjacentNums(y, prev, current, next);

                    if(adjacentNums != null && adjacentNums.length > 1) {
                        long product = adjacentNums[0] * adjacentNums[1];
                        total += product;
                    }
                }
            }
        }
        return total;
    }

    private static boolean isDigit(char c) {
        if(c >= '0' && c <= '9') return true;
        return false;
    }

    private static int[] getAdjacentNums(int pos, String prev, String current, String next) {

        List<String> strings = new ArrayList<>();

        // check current before
        if(pos > 0 && isDigit(current.charAt(pos-1))) {
            int i = pos-1;
            // consume backwards
            for(; i >= 0; i--) {
                if(!isDigit(current.charAt(i))) {
                    break;
                }
            }
            // add to list
            strings.add(current.substring(i+1, pos));
        }

        //check current after
        if(pos < current.length() && current.charAt(pos+1) >= '0' && current.charAt(pos+1) <= '9') {
            int i = pos+1;
            // consume forwards
            for(; i < current.length(); i++) {
                if(!isDigit(current.charAt(i))) {
                    break;
                }
            }
            // add to list
            strings.add(current.substring(pos+1, i));
        }

        // check prev
        int start = Math.max(0, pos-1);
        int end = Math.min(current.length(), pos+1);
        scanRow(pos, prev, strings, start, end);

        // check next
        scanRow(pos, next, strings, start, end);


        System.out.println(strings);

        return strings.stream()
                .mapToInt(Integer::parseInt)
                .toArray();

    }

    private static void scanRow(int pos, String prev, List<String> strings, int start, int end) {
        if(!prev.isEmpty()) {
            for (int x = start; x <= end; x++) {
                if (prev.charAt(x) >= '0' && prev.charAt(x) <= '9') {
                    // consume both ways
                    int numStart = x;
                    int numEnd = x;
                    while(numStart >= 0 && isDigit(prev.charAt(numStart))) {
                        numStart--;
                    }
                    while(numEnd < prev.length() && isDigit(prev.charAt(numEnd))) {
                        numEnd++;
                    }
                    // store
                    strings.add(prev.substring(numStart+1, numEnd));

                    //. not an asterisk at begin or end of line so might have 2 numbers adjacent
                    if(pos > 0 && pos < prev.length()-1) {
                        // if direct below is a number then not possible to have 2 adjacent so now break
                        // otherwise continue around the loop looking for a second number
                        if(isDigit(prev.charAt(pos))) {
                           break;
                        }
                    }
                }
            }
        }
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
