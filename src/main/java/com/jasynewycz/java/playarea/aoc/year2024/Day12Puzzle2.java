package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day12Puzzle2 {


    public static final int DIR_UP = 0;
    public static final int DIR_RIGHT = 1;
    public static final int DIR_DOWN = 2;
    public static final int DIR_LEFT = 3;


    static class Shape {
        char character;
        char[][] shape;

        Shape(char c, int width, int height) {
            this.character=c;
            shape = new char[width][height];
        }

        @Override
        public String toString() {
            printData(shape);
            return "Shape{" +
                    "character=" + character +
                    "shape=" + Arrays.toString(shape) +
                    '}';

        }

        public int calcResult() {
            int areaCount = 0;
            Set<String> sides = new HashSet<>();

            // find the "start" of each edge and add to set as a string
            for (int x = 0; x < shape.length; x++) {
                for( int y = 0; y < shape[x].length; y++) {
                    if(shape[x][y] == character) {
                        areaCount++;

                        if(x == 0 || shape[x-1][y] == 0) {
                            int tempY = y;
                            while (tempY != 0 && (x == 0 || shape[x-1][tempY] == 0) && shape[x][tempY] == character) {
                                tempY--;
                            }
                            sides.add("A:" + "x:" + x + "y:" + tempY);
                        }
                        if(x == shape.length-1 || shape[x+1][y] == 0) {
                            int tempY = y;
                            while (tempY != 0 && (x == shape.length-1 || shape[x+1][tempY] == 0) && shape[x][tempY] == character) {
                                tempY--;
                            }
                            sides.add("B:" + "x:" + x + "y:" + tempY);
                        }
                        if(y == 0 || shape[x][y-1] == 0) {
                            int tempX = x;
                            while (tempX != 0 && (y == 0 || shape[tempX][y-1] == 0) && shape[tempX][y] == character) {
                                tempX--;
                            }
                            sides.add("C:" + "x:" + tempX + "y:" + y);
                        }
                        if(y == shape[x].length-1 || shape[x][y+1] == 0) {
                            int tempX = x;
                            while (tempX != 0 && (y == shape[x].length-1 || shape[tempX][y+1] == 0) && shape[tempX][y] == character) {
                                tempX--;
                            }
                            sides.add("D:" + "x:" + tempX + "y:" + y);
                        }
                    }
                }
            }
            return (areaCount * sides.size());
        }
    }



    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day12puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;


            char[][] data = new char[lines.length][lines[0].length()];
            int x = 0;
            int y = 0;
            for (String line : lines) {
                data[x] = line.toCharArray();
                x++;
            }

            List<Shape> shapes = new ArrayList<>();

            // create "shapes" flood fill type algorithm needed?
            for(x = 0; x < data.length; x++) {
                for(y = 0; y < data[x].length; y++) {

                    if(data[x][y] != 0) {
                        Shape shape = new Shape(data[x][y], data.length, data[x].length);
                        extractShape(data, data[x][y], x, y, shape, -1);
                        shapes.add(shape);
                    }
                }
            }

            for (Shape shape: shapes) {
                total += shape.calcResult();
            }

            System.out.println("Result: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void extractShape(char[][] data, char current, int x, int y, Shape shape, int direction) {
        if(data[x][y] == current) {
            shape.shape[x][y] = current;
            data[x][y] = 0;

            if(x > 0 && data[x-1][y] == current && direction != DIR_DOWN) {
                extractShape(data, current, x-1, y, shape, DIR_UP);
            }
            if(x < data.length-1 && data[x+1][y] == current && direction != DIR_UP) {
                extractShape(data, current, x+1, y, shape, DIR_DOWN);
            }
            if(y > 0 && data[x][y-1] == current && direction != DIR_RIGHT) {
                extractShape(data, current, x, y-1, shape, DIR_LEFT);
            }
            if(y < data[x].length-1 && data[x][y+1] == current && direction != DIR_LEFT) {
                extractShape(data, current, x, y+1, shape, DIR_RIGHT);
            }
        }
    }

    static void printData(char[][] data) {
        System.out.println();
        for(int x = 0; x < data.length; x++) {
            for(int y = 0; y < data[x].length; y++) {

                System.out.print(data[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
