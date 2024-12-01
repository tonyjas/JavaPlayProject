package com.jasynewycz.java.playarea.aoc.year2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day16puzzle2 {

    enum Direction {
        NORTH, SOUTH, EAST, WEST
    }

    static class Beam {
        boolean end = false;
        int x, y;
        Direction direction;

        public Beam(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Beam beam = (Beam) o;
            return x == beam.x && y == beam.y && direction == beam.direction;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, direction);
        }
    }


    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2023/day16puzzle1realdata.txt"));

        char[][] data = new char[lines.size()][lines.get(0).length()];

        for(int x = 0; x < data.length; x++) {

            for(int y = 0; y < data[x].length; y++) {
                data[x][y] = lines.get(x).charAt(y);
            }
        }

        int topScore = 0;

        for(int x = 0; x < data.length; x++) {

            List<Beam> beams = new ArrayList<>();
            beams.add(new Beam(x, -1, Direction.EAST));

            int score = calculateEnergized(beams, data);
            if(score > topScore) {
                topScore = score;
            }

            beams = new ArrayList<>();
            beams.add(new Beam(x, data[x].length, Direction.WEST));

            score = calculateEnergized(beams, data);
            if(score > topScore) {
                topScore = score;
            }
        }

        for(int y = 0; y < data[0].length; y++) {

            List<Beam> beams = new ArrayList<>();
            beams.add(new Beam(-1, y, Direction.SOUTH));

            int score = calculateEnergized(beams, data);
            if(score > topScore) {
                topScore = score;
            }

            beams = new ArrayList<>();
            beams.add(new Beam(data.length, y, Direction.NORTH));

            score = calculateEnergized(beams, data);
            if(score > topScore) {
                topScore = score;
            }
        }

        System.out.println(topScore);
    }

    private static int calculateEnergized(List<Beam> beams, char[][] data) {

        char[][] energizedData = new char[data.length][data[0].length];

        Set<Beam> points = new HashSet<>();

        for(int i = 0; i < data.length; i++) {

            for(int j = 0; j < data[i].length; j++) {

                energizedData[i][j] = '.';
            }
        }

        // "game" loop
        while(true) {

            boolean movingBeams = false;
            List<Beam> newBeams = new ArrayList<>();
            List<Beam> removeBeams = new ArrayList<>();

            //iterate through the beams
            for(Beam beam : beams) {

                move(beam, data);

                if(!points.add(new Beam(beam.x, beam.y, beam.direction))) {
                    removeBeams.add(beam);
                }

                if(!beam.end) {
                    movingBeams = true;
                }

                energizedData[beam.x][beam.y] = '#';

                switch(data[beam.x][beam.y]) {
                    case '.': {
                        break;
                    }
                    case '|': {
                        if(beam.direction == Direction.EAST || beam.direction == Direction.WEST) {
                            newBeams.add(new Beam(beam.x, beam.y, Direction.SOUTH));
                            beam.direction = Direction.NORTH;
                        }
                        break;
                    }
                    case '-': {
                        if(beam.direction == Direction.NORTH || beam.direction == Direction.SOUTH) {
                            newBeams.add(new Beam(beam.x, beam.y, Direction.EAST));
                            beam.direction = Direction.WEST;
                        }
                        break;
                    }
                    case '/': {
                        if(beam.direction == Direction.NORTH) {
                            beam.direction = Direction.EAST;
                            break;
                        }
                        if(beam.direction == Direction.SOUTH) {
                            beam.direction = Direction.WEST;
                            break;
                        }
                        if(beam.direction == Direction.EAST) {
                            beam.direction = Direction.NORTH;
                            break;
                        }
                        if(beam.direction == Direction.WEST) {
                            beam.direction = Direction.SOUTH;
                            break;
                        }
                    }
                    case '\\': {
                        if(beam.direction == Direction.NORTH) {
                            beam.direction = Direction.WEST;
                            break;
                        }
                        if(beam.direction == Direction.SOUTH) {
                            beam.direction = Direction.EAST;
                            break;
                        }
                        if(beam.direction == Direction.EAST) {
                            beam.direction = Direction.SOUTH;
                            break;
                        }
                        if(beam.direction == Direction.WEST) {
                            beam.direction = Direction.NORTH;
                            break;
                        }
                    }
                }



            }

            if(newBeams.size() > 0) {
                beams.addAll(newBeams);
            }
            if(removeBeams.size() > 0) {
                beams.removeAll(removeBeams);
            }

            if(beams.size() == 0) {
                break;
            }

            if(!movingBeams) {
                break;
            }
        }

        int countEnergized = 0;

        for(int x = 0; x < data.length; x++) {

            for (int y = 0; y < data[x].length; y++) {

                if(energizedData[x][y] == '#') {
                    countEnergized++;
                }
            }
        }
        return countEnergized;
    }

    private static void move(Beam beam, char[][] data) {

        if(!beam.end) {
            switch(beam.direction) {
                case NORTH: {
                    if (beam.x == 0) {
                        beam.end = true;
                    } else {
                        beam.x--;
                    }
                    break;
                }
                case SOUTH: {
                    if (beam.x == data[0].length-1) {
                        beam.end = true;
                    } else {
                        beam.x++;
                    }
                    break;
                }
                case EAST: {
                    if (beam.y == data.length-1) {
                        beam.end = true;
                    } else {
                        beam.y++;
                    }
                    break;
                }
                case WEST: {
                    if (beam.y == 0) {
                        beam.end = true;
                    } else {
                        beam.y--;
                    }
                    break;
                }
            }
        }
    }


    private static void printData(char[][] data) {

        for(int x = 0; x < data.length; x++) {

            for (int y = 0; y < data[x].length; y++) {

                System.out.print(data[x][y]);
            }
            System.out.println();
        }
    }

}
