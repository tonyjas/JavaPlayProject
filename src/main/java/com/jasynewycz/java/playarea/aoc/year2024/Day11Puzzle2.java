package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day11Puzzle2 {

    public static final int MAX_DEPTH = 75;

    static Map<String, Long> cache = new HashMap<>();

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day11puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            List<Long> data = new ArrayList<>();
            String[] items = lines[0].split(" ");
            for(String item: items) {
                data.add(Long.parseLong(item));
            }

            for(long value: data) {
                long c = search(value, 0);

                total += c;

                System.out.println(value + "\t" + c + "\t" + total);

            }
            System.out.println("Result: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static long search(long value, int depth) {
        if(depth == MAX_DEPTH) {
            return 1;
        }
        String cacheKey = "D"+depth+"V"+value;
        if(cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }
        depth++;
        long ret = 0;
        if(value == 0) {
            ret += search(1L, depth);
        } else if((""+value).length() % 2 == 0) {
            String s = ""+value;
            ret += search(Long.parseLong(s.substring(0, s.length()/2)), depth);
            ret += search(Long.parseLong(s.substring(s.length()/2)), depth);
        } else {
            ret += search(value*2024, depth);
        }
        cache.put(cacheKey, ret);
        return ret;
    }
}
