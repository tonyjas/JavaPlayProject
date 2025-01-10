package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Day20Puzzle2 {


    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day20puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            int x = 0;
            int y = 0;

            char[][] data = new char[lines.length][lines[0].length()];

            for (int i = 0; i < lines.length; i++) {

                if(lines[i].contains("S")) {
                    x = i;
                    y = lines[i].indexOf("S");
                }
                data[i] = lines[i].toCharArray();
            }

            Map<String, Long> times = new HashMap<>();

            findPath(data, times, x, y, 0L);

            System.out.println(times);

            Map<Long, Long> cheats = new HashMap<>();

            findCheats(data, times, x, y, cheats, 0);

            System.out.println(cheats);

            for(Map.Entry<Long, Long> cheat : cheats.entrySet()) {
                if(cheat.getKey() > 99) {
                    total += cheat.getValue();
                }
            }

            System.out.println("Result: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    private static void findPath(char[][] data, Map<String, Long> times, int x , int y, long score) {

        while (data[x][y] != 'E') {

            if((data[x-1][y] == '.' || data[x-1][y] == 'E') && !times.containsKey("x:"+(x-1)+"y:"+y)) {
                times.put("x:"+(x-1)+"y:"+y, ++score);
                x--;
            } else if((data[x+1][y] == '.' || data[x+1][y] == 'E')  && !times.containsKey("x:"+(x+1)+"y:"+y)) {
                times.put("x:"+(x+1)+"y:"+y, ++score);
                x++;
            } else if((data[x][y-1] == '.' || data[x][y-1] == 'E') && !times.containsKey("x:"+x+"y:"+(y-1))) {
                times.put("x:"+x+"y:"+(y-1), ++score);
                y--;
            } else if((data[x][y+1] == '.' || data[x][y+1] == 'E') && !times.containsKey("x:"+x+"y:"+(y+1))) {
                times.put("x:"+x+"y:"+(y+1), ++score);
                y++;
            }
        }

        if (data[x][y] == 'E') {
            times.put("x:" + x + "y:" + y, score);
        }

    }


    private static void findCheats(char[][] data, Map<String, Long> times, int x, int y, Map<Long, Long> cheats, int score) {

        while (data[x][y] != 'E') {

            String key = "x:"+(x-2)+"y:"+y;
            if(data[x-1][y] == '#' && times.containsKey(key)) {
                long timeSaved = (times.get(key) - score) - 2;
                if(timeSaved > 0) {
                    if (cheats.containsKey(timeSaved)) {
                        cheats.replace(timeSaved, cheats.get(timeSaved) + 1);
                    } else {
                        cheats.put(timeSaved, 1L);
                    }
                }
            }
            key = "x:"+(x+2)+"y:"+y;
            if(data[x+1][y] == '#' && times.containsKey(key)) {
                long timeSaved = (times.get(key) - score) - 2;
                if(timeSaved > 0) {
                    if (cheats.containsKey(timeSaved)) {
                        cheats.replace(timeSaved, cheats.get(timeSaved) + 1);
                    } else {
                        cheats.put(timeSaved, 1L);
                    }
                }
            }
            key = "x:"+x+"y:"+(y-2);
            if(data[x][y-1] == '#' && times.containsKey(key)) {
                long timeSaved = (times.get(key) - score) - 2;
                if(timeSaved > 0) {
                    if (cheats.containsKey(timeSaved)) {
                        cheats.replace(timeSaved, cheats.get(timeSaved) + 1);
                    } else {
                        cheats.put(timeSaved, 1L);
                    }
                }
            }
            key = "x:"+x+"y:"+(y+2);
            if(data[x][y+1] == '#' && times.containsKey(key)) {
                long timeSaved = (times.get(key) - score) - 2;
                if(timeSaved > 0) {
                    if (cheats.containsKey(timeSaved)) {
                        cheats.replace(timeSaved, cheats.get(timeSaved) + 1);
                    } else {
                        cheats.put(timeSaved, 1L);
                    }
                }
            }


            // continue on path
            if(data[x-1][y] == '.' && times.containsKey("x:"+(x-1)+"y:"+y) && (!times.containsKey("x:"+x+"y:"+y) || times.get("x:"+(x-1)+"y:"+y) > times.get("x:"+x+"y:"+y))) {
                x--;
            } else if(data[x+1][y] == '.' && times.containsKey("x:"+(x+1)+"y:"+y) && (!times.containsKey("x:"+x+"y:"+y) || times.get("x:"+(x+1)+"y:"+y) > times.get("x:"+x+"y:"+y))) {
                x++;
            } else if(data[x][y-1] == '.' && times.containsKey("x:"+x+"y:"+(y-1)) && (!times.containsKey("x:"+x+"y:"+y) || times.get("x:"+x+"y:"+(y-1)) > times.get("x:"+x+"y:"+y))) {
                y--;
            } else if(data[x][y+1] == '.' && times.containsKey("x:"+x+"y:"+(y+1)) && (!times.containsKey("x:"+x+"y:"+y) || times.get("x:"+x+"y:"+(y+1)) > times.get("x:"+x+"y:"+y))) {
                y++;
            } else {
                break;
            }
            score++;
        }
    }
}
