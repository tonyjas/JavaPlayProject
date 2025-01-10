package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class Day9Puzzle1 {


    public static void main(String[] args) {


        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day9puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            char[] temp = lines[0].toCharArray();

            int[] dataArray = new int[temp.length];
            for(int i = 0; i < temp.length; i++) {
                dataArray[i] = Integer.parseInt(""+temp[i]);
            }


            List<Integer> output = new LinkedList<>();

            // 1. count no of files
            int noOfFiles = (int)(dataArray.length +1) / 2;

            boolean isFileAtEnd = isFile(dataArray.length-1);
            int currentHighFileNumber = noOfFiles-1;
            int currentLowFileNumber = 0;


            int highPosition = dataArray.length-1;

            // work both ways using mod 2 to see if we have a file or free block
            for (int lowPosition = 0; lowPosition <= highPosition; lowPosition++) {
                if (isFile(lowPosition)) {
                    //1. getLeftFile and expand into new ds
                    while (dataArray[lowPosition] > 0) {
                        output.add(currentLowFileNumber);
                        dataArray[lowPosition]--;
                    }
                    currentLowFileNumber++;
                } else {

                    //2. fill gap with data from last file
                    for (int x = 0; x < dataArray[lowPosition];) {
                        // jump to file if on free space
                        if(!isFile(highPosition)) {
                            highPosition--;
                        }
                        if (dataArray[highPosition] > 0) {
                            output.add(currentHighFileNumber);
                            x++;
                            dataArray[highPosition]--;
                        } else {
                            currentHighFileNumber--;
                            highPosition -= 2;
                            if(lowPosition > highPosition) {
                                break;
                            }
                        }
                    }
                }
            }

            System.out.println(output);
            // create new array or just gen the checksum as we go

            for (int x = 0; x < output.size(); x++) {

                total += ((long) x * output.get(x));
            }

            System.out.println("Total: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static boolean isFile(int value) {
        if (value == 0) {
            return true;
        }
        return (value % 2 == 0);
    }
}