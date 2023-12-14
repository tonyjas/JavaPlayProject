package com.jasynewycz.java.playarea.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day13puzzle2 {


    public static void main(String[] args)  throws IOException {

        List<String> lines = Files.readAllLines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day13puzzle1realdata.txt"));

        List<String> temp = new ArrayList<>();
        List<List<String>> games = new ArrayList<>();

        // split down into our "squares"
        for(int x = 0; x < lines.size(); x++) {
            if(lines.get(x).isBlank()) {
                games.add(temp);
                temp = new ArrayList<>();

            } else {
                temp.add(lines.get(x));
            }
        }

        if(!temp.isEmpty()) {
            games.add(temp);
        }

        int[] numbers = new int[games.size()];
        int gameCounter = 0;

        // for each game
        for(List<String> game : games) {

            int symmetryRow = -1;
            int symmetryCol = -1;
            boolean potentialSymmetry = false;
            int diffXIndex = -1;

            // check rows for mirror
            for(int x = 0; x < game.size()-1; x++) {
                int diffCount = noOfDiffs(game.get(x), game.get(x+1));
                diffXIndex = x;
                if (diffCount < 2) {
                    potentialSymmetry = true;
                    // loop and expand from this center point
                    for(int y = x-1, z = x+2; y >= 0 && z < game.size(); y--, z++) {
                        int t = noOfDiffs(game.get(y), game.get(z));
                        if (t == 1) {
                            diffXIndex = y;
                        }
                        diffCount += t;
                        if(!(diffCount < 2)) {
                            potentialSymmetry = false;
                            break;
                        }
                    }
                    if(potentialSymmetry) {
                        if(diffCount == 1) { // only use the value if we have found 1 diff during check
                            symmetryRow = x;
                            break;
                        }
                    }
                }
            }

            if(symmetryRow != -1) {
                numbers[gameCounter++] = (symmetryRow+1)*100;
                continue;
            }


            // transpose the data to enable String compare
            char[][] data = new char[game.get(0).length()][game.size()];

            for(int x = 0; x < game.get(0).length(); x++) {
                for(int y = 0; y < game.size(); y++) {
                    data[x][y] = game.get(y).charAt(x);
                }
            }

//            for(char[] temp2 : data) {
//                System.out.println(new String(temp2).intern());
//            }



            // check cols for mirror
            for(int x = 0; x < data.length; x++) {
                int diffCount = noOfDiffs(new String(data[x]), new String(data[x+1]));
                diffXIndex = x;
                if (diffCount < 2) {
                    potentialSymmetry = true;
                    // loop and expand from this center point
                    for (int y = x - 1, z = x + 2; y >= 0 && z < data.length; y--, z++) {
                        int t = noOfDiffs(new String(data[y]), new String(data[z]));
                        if (t == 1) {
                            diffXIndex = y;
                        }
                        diffCount += t;
                        if(!(diffCount < 2)) {
                            potentialSymmetry = false;
                            break;
                        }
                    }

                    if(potentialSymmetry) {
                        if(diffCount == 1) {
                            symmetryCol = x;
                            break;
                        }
                    }
                }
            }

            if(symmetryCol != -1) {
                numbers[gameCounter++] = (symmetryCol+1);
            }
        }

        System.out.println(Arrays.toString(numbers));
        long total = Arrays.stream(numbers).sum();

        System.out.println(total);
    }


    private static int noOfDiffs(String s1, String s2) {
        int count = 0;
        for(int x = 0; x < s1.length(); x++) {
            if(s1.charAt(x) != s2.charAt(x)) {
                count++;
            }
        }
        return count;
    }
}
