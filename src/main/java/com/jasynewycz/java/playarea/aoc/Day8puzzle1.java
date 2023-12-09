package com.jasynewycz.java.playarea.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day8puzzle1 {

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

        String steps = lines.get(0);
        System.out.println(steps);

        for(int x = 2; x < lines.size(); x++) {
            String[] parts = Arrays.stream(lines.get(x).split("[ =,()]")).filter(i -> !i.isEmpty()).collect(Collectors.toList()).toArray(new String[3]);
            System.out.println(Arrays.toString(parts));

            map.put(parts[0], new Node(parts[0], parts[1], parts[2]));
        }

        String value = "AAA";
        int noOfSteps = 0;
        int x = 0;
        while(!value.equals("ZZZ")) {
            noOfSteps++;
            // wrap to beginning of string
            if(x == steps.length()) {
                x = 0;
            }

            Node current = map.get(value);
            if(steps.charAt(x++) == 'L') {
                value = current.left;
            } else {
                value = current.right;
            }
        }

        System.out.println(noOfSteps);
    }
}
