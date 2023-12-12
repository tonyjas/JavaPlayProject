package com.jasynewycz.java.playarea.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day11puzzle1 {

    record Point(int x, int y) {

    }
    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day11puzzle1testdata.txt"));

        List<String> newLines = new ArrayList<>();

        Set<Integer> galaxyCols = new TreeSet<>();
        List<Integer> noGalaxyCols = new ArrayList<>();

        // create grid of galaxies and add lines to account for time slip/expansion
        for (int x = 0; x < lines.size() ; x++) {
            newLines.add(lines.get(x));

            if(!lines.get(x).contains("#")) {
                newLines.add(lines.get(x));
            } else {
                String temp = lines.get(x);
                int start = 0;
                do {
                    start = temp.indexOf("#", start);
                    if(start != -1) {
                        galaxyCols.add(start);
                    }
                    start++;
                } while (start != 0);
            }
        }

        System.out.println(newLines);
        System.out.println(galaxyCols);

        for (int x = 0; x < lines.get(0).length(); x++) {
            if(!galaxyCols.contains(x)) {
                noGalaxyCols.add(x);
            }
        }

        System.out.println(noGalaxyCols);

        char[][] data = new char[newLines.size()][newLines.get(0).length() + noGalaxyCols.size()];
        Map<Integer, Point> galaxies = new HashMap<>();

        int galaxyNumber = 1;

        for(int x = 0; x < newLines.size(); x++) {
            String line = newLines.get(x);
            int extraColCounter = 0;

            for (int y = 0; y < line.length(); y++) {

                data[x][y+extraColCounter] = line.charAt(y);
                if (data[x][y+extraColCounter] == '#') {
                    galaxies.put(galaxyNumber++, new Point(x, y+extraColCounter));
                }
                if (noGalaxyCols.contains(y)) {
                    extraColCounter++;
                    data[x][y+extraColCounter] = '.';
                }
            }
        }

        System.out.println(galaxies);

        int total = 0;
        // now find the distances between
        for (int x = 1; x <= galaxies.size(); x++) {
            Point galaxyA = galaxies.get(x);
            for(int y = x+1; y <= galaxies.size(); y++) {
                Point galaxyB = galaxies.get(y);
                total += Math.abs(galaxyA.x - galaxyB.x) + Math.abs(galaxyA.y - galaxyB.y);
            }
        }

        System.out.println(total);
    }
}
