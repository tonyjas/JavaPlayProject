package com.jasynewycz.java.playarea.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day9puzzle1 {


    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day9puzzle1realdata.txt"));

        System.out.println(lines);

        long total = 0;

        for(String line : lines) {

            int[] nums = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();

            boolean allZero = true;
            List<List<Integer>> lists = new ArrayList<>();

            List<Integer> initial = new ArrayList<>();
            for(int i = 0; i < nums.length; i++) {
                initial.add(nums[i]);
            }

            lists.add(initial);

            int listNo = 0;

            do {
                allZero = true;

                List<Integer> newDiffs = new ArrayList<>();
                List<Integer> input = lists.get(listNo++);

                for (int x = 0; x < input.size()-1; x++) {
                    int diff = input.get(x+1) - input.get(x);
                    newDiffs.add(diff);
                    if(diff != 0) {
                        allZero = false;
                    }

                }

                lists.add(newDiffs);

            } while(allZero == false);

            int value = 0;

            for(int x = lists.size()-2; x >= 0; x--) {
                value += lists.get(x).getLast();

                if(x == 0) {
                    total += value;
                }
            }
        }


        System.out.println(total);

    }
}
