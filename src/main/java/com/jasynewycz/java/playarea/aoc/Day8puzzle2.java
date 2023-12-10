package com.jasynewycz.java.playarea.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day8puzzle2 {

    static class Node {
        String value;
        String left;
        String right;

        public Node(String value, String left, String right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day8puzzle1realdata.txt"));
        HashMap<String, Node> map = new HashMap<>();
        Set<String> starterSet = new HashSet<>();

        String steps = lines.get(0);
        System.out.println(steps);

        for(int x = 2; x < lines.size(); x++) {
            String[] parts = Arrays.stream(lines.get(x).split("[ =,()]")).filter(i -> !i.isEmpty()).collect(Collectors.toList()).toArray(new String[3]);

            map.put(parts[0], new Node(parts[0], parts[1], parts[2]));
            if(parts[0].endsWith("A")) {
                starterSet.add(parts[0]);
            }
        }

        String[] values = starterSet.toArray(new String[0]);

        System.out.println("values: " + Arrays.toString(values));

        int noOfSteps = 0;
        int x = 0;



/*
        while(!Arrays.stream(values).allMatch(s -> s.endsWith("Z"))) {

            if(noOfSteps % 100_000_000 == 0) {
                System.out.println("Steps: " + noOfSteps);
            }
            // wrap to beginning of string
            if(x >= steps.length()) {
                x = 0;
            }

            for(int i = 0; i < values.length; i++) {
                if(values[i].endsWith("Z")) {
                    System.out.println("Thread: " + i + "\t" + values[i] + "\t" + noOfSteps);
                }
                Node current = map.get(values[i]);
                if (steps.charAt(x) == 'L') {
                    values[i] = current.left;
                } else {
                    values[i] = current.right;
                }
            }
            x++;
            noOfSteps++;
        }
        **/

        long[] intSeedValues = new long[] {17141, 13207, 18827, 22199, 16579, 12083};
        long[] currentValues = Arrays.copyOf(intSeedValues, 6);

        while(true) {
            // if we have 1 distinct value they all match!
            if(Arrays.stream(currentValues).distinct().count() == 1) {
                break;
            }

            long lowest = Long.MAX_VALUE;
            int lowestIndex = -1;
            // increment lowest value
            for(int i = 0; i < currentValues.length; i++) {
                if(currentValues[i]+intSeedValues[i] < lowest) {
                    lowestIndex = i;
                    lowest = currentValues[i]+intSeedValues[i];
                }
            }

            currentValues[lowestIndex] += intSeedValues[lowestIndex];
            if(x++ % 100_000_000 == 0) {
                System.out.println(Arrays.toString(currentValues));
            }

        }

        System.out.println("Answer: " + Arrays.toString(currentValues));

        System.out.println(noOfSteps);
    }
}
