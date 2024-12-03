package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2Puzzle2 {

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day2puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            for (String line: lines) {

                List<Integer> values = Arrays.stream(line.split(" ")).map(Integer::valueOf).toList();

                int issueIndex = isSafe(values);


                if (issueIndex != -1) {
                    for( int x = Math.max(0, issueIndex-1); x < Math.min(issueIndex+2, values.size()+1); x++) {

                        List<Integer> temp = new ArrayList<>(values);
                        temp.remove(x);
                        if(isSafe(temp) == -1) {
                            issueIndex = -1;
                            break;
                        }
                    }
                }

                if (issueIndex == -1) {
                    total++;
                }
            }

            System.out.println("Total: " + total);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int isSafe(List<Integer> values) {
        boolean desc = false;
        for (int x = 0; x < values.size()-1; x++) {

            int first = values.get(x);
            int second = values.get(x+1);

            if( x == 0 && first > second) {
                desc = true;
            }
            if(desc && first <= second) {
                return x;
            }
            if(!desc && first >= second) {
                return x;
            }
            if(Math.abs(first - second) > 3) {
                return x;
            }
        }
        return -1;
    }
}
