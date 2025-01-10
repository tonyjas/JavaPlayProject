package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day17Puzzle2 {


    static long regA = 0;
    static long regB = 0;
    static long regC = 0;

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day17puzzle1testdata.txt")).toList().toArray(new String[0]);

            String programString = lines[4].substring(lines[4].indexOf(": ")+ 2);

            int[] program = Arrays.stream(programString.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            List<Integer> output;
            Map<Integer, Long> values = new HashMap<>();


            System.out.println(Arrays.toString(program));

            int currentDigit = program.length;
            long max = (long)Math.pow(8, currentDigit)-1;

            long delta = (long)Math.pow(8, currentDigit-1);

            for (long x = delta; x < max; x+= delta) {

                regA = x;
                regB = Long.parseLong(lines[1].substring(lines[1].indexOf("B: ") + 3));
                regC = Long.parseLong(lines[2].substring(lines[2].indexOf("C: ") + 3));

                output = new ArrayList<>();

                for (int counter = 0; counter < program.length; counter += 2) {

                    int opcode = program[counter];
                    int literalOperand = program[counter + 1];
                    long comboOperand = getComboOperandValue(program[counter + 1]);

                    switch (opcode) {
                        case 0:
                            regA = (long) (regA / Math.pow(2, comboOperand));
                            break;
                        case 1:
                            regB = regB ^ literalOperand;
                            break;
                        case 2:
                            regB = comboOperand % 8;
                            break;
                        case 3:
                            if (regA != 0) {
                                counter = literalOperand - 2;
                                continue;
                            }
                            break;
                        case 4:
                            regB = regB ^ regC;
                            break;
                        case 5:
                            output.add((int)comboOperand % 8);
                            break;
                        case 6:
                            regB = (int) (regA / Math.pow(2, comboOperand));
                            break;
                        case 7:
                            regC = (int) (regA / Math.pow(2, comboOperand));
                            break;
                    }
                }

                System.out.println(x + ":\t" + Long.toOctalString(x) + ":\t" + delta  + ":\t" + Long.toOctalString(delta)  + ":\t" + Arrays.toString(output.toArray()));

                int matchesUntil = matches(output, program, currentDigit);

                if (matchesUntil == -1) {

                    values.put(currentDigit, x);

                    currentDigit--;
                    delta = (long)Math.pow(8, currentDigit-1);
                    if(currentDigit == 0) {
                        break;
                    }
                } else {
                    if (currentDigit-1 != matchesUntil) {
                        currentDigit = matchesUntil;
                        delta = (long) Math.pow(8, currentDigit - 1);
                        if (values.containsKey(currentDigit)) {
                            x = values.get(currentDigit);
                        }
                    }
                }
                if (currentDigit == -1) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long getComboOperandValue(int v) {
        switch (v) {
            case 4: return regA;
            case 5: return regB;
            case 6: return regC;
            default: return v;
        }
    }

    private static int matches(List<Integer> output, int[] program, int from) {
        for(int x = Math.max(0, from-1); x < program.length; x++) {
            if(output.get(x) != program[x]) {
                return x;
            }
        }
        return -1;
    }
}
