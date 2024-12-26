package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day15Puzzle1 {

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day15puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            char[][] data = new char[lines.length][lines[0].length()];
            int posX = 0;
            int posY = 0;

            int x = 0;
            String line;
            do {
                line = lines[x];
                if(line.contains("@")) {
                    posX = x;
                    posY = line.indexOf('@');
                }
                data[x++] = line.toCharArray();
            } while(line.startsWith("#"));

            for (; x < lines.length; x++) {
                if(lines[x].isBlank()) {
                    continue;
                }
                // do the work
                for (int y = 0; y < lines[x].length(); y++) {
                    char direction = lines[x].charAt(y);

                    switch(direction) {
                        case '^':
                            if (data[posX - 1][posY] == '.') {
                                data[posX][posY] = '.';
                                data[posX - 1][posY] = '@';
                                posX--;
                            } else if (data[posX - 1][posY] == '#') {
                                // do nothing
                            } else if (data[posX - 1][posY] == 'O') {
                                int temp = posX - 1;
                                while (data[temp][posY] == 'O') {
                                    temp--;
                                }
                                if (data[temp][posY] == '.') {
                                    data[temp][posY] = 'O';
                                    data[posX][posY] = '.';
                                    data[posX - 1][posY] = '@';
                                    posX--;
                                }
                            }
                            break;
                        case 'v':
                            if (data[posX + 1][posY] == '.') {
                                data[posX][posY] = '.';
                                data[posX + 1][posY] = '@';
                                posX++;
                            } else if (data[posX + 1][posY] == '#') {
                                // do nothing
                            } else if (data[posX + 1][posY] == 'O') {
                                int temp = posX + 1;
                                while (data[temp][posY] == 'O') {
                                    temp++;
                                }
                                if (data[temp][posY] == '.') {
                                    data[temp][posY] = 'O';
                                    data[posX][posY] = '.';
                                    data[posX + 1][posY] = '@';
                                    posX++;
                                }
                            }
                            break;
                        case '>':
                            if (data[posX][posY + 1] == '.') {
                                data[posX][posY] = '.';
                                data[posX][posY + 1] = '@';
                                posY++;
                            } else if (data[posX][posY + 1] == '#') {
                                // do nothing
                            } else if (data[posX][posY + 1] == 'O') {
                                int temp = posY + 1;
                                while (data[posX][temp] == 'O') {
                                    temp++;
                                }
                                if (data[posX][temp] == '.') {
                                    data[posX][temp] = 'O';
                                    data[posX][posY] = '.';
                                    data[posX][posY + 1] = '@';
                                    posY++;
                                }
                            }
                            break;
                        case '<':
                            if (data[posX][posY - 1] == '.') {
                                data[posX][posY] = '.';
                                data[posX][posY - 1] = '@';
                                posY--;
                            } else if (data[posX][posY - 1] == '#') {
                                // do nothing
                            } else if (data[posX][posY - 1] == 'O') {
                                int temp = posY - 1;
                                while (data[posX][temp] == 'O') {
                                    temp--;
                                }
                                if (data[posX][temp] == '.') {
                                    data[posX][temp] = 'O';
                                    data[posX][posY] = '.';
                                    data[posX][posY - 1] = '@';
                                    posY--;
                                }
                            }
                            break;
                    }
                }
            }

            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {

                    if (data[i][j] == 'O') {
                       total += (j + (i * 100L));
                    }
                }
            }

            printData(data);

            System.out.println("Result: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printData(char[][] data) {

        for(int x = 0; x < data.length; x++) {
            for(int y = 0; y < data[x].length; y++) {
                if(data[x][y] != 0) {
                    System.out.print(data[x][y]);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
