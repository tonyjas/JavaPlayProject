package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day11Puzzle1 {


    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day11puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            List<Long> data = new ArrayList<>();
            String[] items = lines[0].split(" ");
            for(String item: items) {
                data.add(Long.parseLong(item));
            }

            for(int x = 0; x < 25; x++) {

                List<Long> tempList = new LinkedList<>();

                for(long value: data) {

                    if(value == 0) {
                        tempList.add(1L);
                    } else if((""+value).length() % 2 == 0) {
                        String s = ""+value;
                        tempList.add(Long.parseLong(s.substring(0, s.length()/2)));
                        tempList.add(Long.parseLong(s.substring(s.length()/2)));
                    } else {
                        tempList.add(value*2024);
                    }
                }

                System.out.println(x + "\t" + tempList.size() + "\t\tDiff:\t"+ (tempList.size()-data.size()));

                data = tempList;
            }

            System.out.println("Result: " + data.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
