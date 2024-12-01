package com.jasynewycz.java.playarea.aoc.year2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day12puzzle1 {

    private static final char UNKNOWN = '?';
    private static final char OPERATIONAL = '.';
    private static final char DAMAGED = '#';


    public static void valid(String configuration, int[] damagedSpringCounts, Set<String> validConfigs) {

        int x = -1;

        if ((x = configuration.indexOf(UNKNOWN)) != -1) {
/*
            int totalDamagedSpringCount = Arrays.stream(damagedSpringCounts).sum();

            StringBuilder b = new StringBuilder(configuration);
            configuration.substring(0, x-1).toCharArray();
            int totalSlots = configuration
            if()*/
/*
            // trim
            if(x > 0 && x < configuration.length()-1) {
                String temp = configuration.substring(0, x - 1);
                String[] sections = temp.split("" + OPERATIONAL);

                for (int y = 0; y < sections.length - 1; y++) {
                    if (sections[y].length() != damagedSpringCounts[y]) {
                        return;
                    }
                }
            }
*/
            valid(configuration.replaceFirst("\\"+UNKNOWN, ""+OPERATIONAL), damagedSpringCounts, validConfigs);
            valid(configuration.replaceFirst("\\"+UNKNOWN, ""+DAMAGED), damagedSpringCounts, validConfigs);
        } else {
            fullValidate(configuration, damagedSpringCounts, validConfigs);
        }
    }

    public static void fullValidate(String configuration, int[] damagedSpringCounts, Set<String> validConfigs) {

        System.out.println(configuration);
        int damagedSpringCountIndex = 0;
        for (int x = 0; x < configuration.length(); x++) {

            if (configuration.charAt(x) == DAMAGED) {
                int size = 0;
                while (x < configuration.length() && configuration.charAt(x) == DAMAGED) {
                    x++;
                    size++;
                }
                if (damagedSpringCountIndex >= damagedSpringCounts.length) {
                    return;
                }
                if (size != damagedSpringCounts[damagedSpringCountIndex]) {
                    return;
                }
                damagedSpringCountIndex++;
            } else if (configuration.charAt(x) == OPERATIONAL) {

            }
        }

        if(damagedSpringCountIndex == damagedSpringCounts.length) {
            validConfigs.add(configuration);
        }
    }

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2023/day12puzzle1testdata.txt"));

        long total = 0;

        for(String line : lines) {

            String[] temp = line.split(" ");

            String configuration = temp[0]+"?"+temp[0]+"?"+temp[0]+"?"+temp[0]+"?"+temp[0];

            temp = (temp[1]+","+temp[1]+","+temp[1]+","+temp[1]+","+temp[1]).split(",");
            int[] damagedSpringCounts = Arrays.stream(temp).mapToInt(Integer::parseInt).toArray();

            System.out.println(configuration + "\t" + Arrays.toString(damagedSpringCounts));

            int configCounts = 0;
            Set<String> validConfigs = new HashSet<>();

            valid(configuration, damagedSpringCounts, validConfigs);

            System.out.println(validConfigs);

            total += validConfigs.size();

            /*
            for(int count : damagedSpringCounts) {
                String currentConfig = configuration;
                boolean isDamaged = false;
                boolean isUnknown = false;

                Set<String> permutations = new HashSet<>();

                char previous = 0;

                for(int y = 0; y < currentConfig.length(); y++) {

                    char state = currentConfig.charAt(y);

                    if (state == DAMAGED) {
                        // try to consume length of count and
                        if(previous == 0 || previous != DAMAGED) {
                            int z = 0;
                            boolean fits = true;
                            while(z < count) {
                                if(currentConfig.charAt(y+z) == DAMAGED) {
                                    fits = false;
                                    break;
                                }
                                z++;
                            }
                            if(fits = true && y+z < currentConfig.length() && currentConfig.charAt(y+z) != DAMAGED) {
                                // ADD TO SET FOR NOW
                                char[] tempConfig = currentConfig.toCharArray();
                                for(int c = y; c < y+z; c++) {
                                    tempConfig[c] = DAMAGED;
                                }
                                permutations.add(new String(tempConfig).intern());
                            }
                        }
                    }

                    if (state == UNKNOWN) {
                        // try to consume length of count and
                        if(previous == 0 || previous != DAMAGED) {
                            int z = 0;
                            boolean fits = true;
                            while(z < count) {
                                if(currentConfig.charAt(y+z) == DAMAGED) {
                                    fits = false;
                                    break;
                                }
                                z++;
                            }
                            if(fits == true && y+z < currentConfig.length() && currentConfig.charAt(y+z) != DAMAGED) {
                                // ADD TO SET FOR NOW
                                char[] tempConfig = currentConfig.toCharArray();
                                for(int c = y; c < y+z; c++) {
                                    tempConfig[c] = DAMAGED;
                                }
                                permutations.add(new String(tempConfig).intern());
                            }
                        }

                    }

                    previous = state;
                }


                System.out.println(permutations);


                //checkPermutations(permutations);
            }
*/        }

        System.out.println(total);
    }
/*

    private boolean isValidConfig(String config, int[] damagedSpringCounts) {

        boolean inDamagedSection = false;
        int sectionIndex = 0;

        for(int x = 0; x < config.length(); x++) {

            if (config.charAt(x) == DAMAGED) {
                if(inDamagedSection) {
                    damagedSpringCounts[sectionIndex]--;
                }
                inDamagedSection = true;
            } else if(inDamagedSection) {
                inDamagedSection = false;
                sectionIndex++;
            }
        }

        return true;
    }*/
}
