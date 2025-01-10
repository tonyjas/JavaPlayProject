package com.jasynewycz.java.playarea.aoc.year2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day17Puzzle1 {

    static int regA = 0;
    static int regB = 0;
    static int regC = 0;

    public static void main(String[] args) {

        try {

            String[] lines = Files.lines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2024/day17puzzle1testdata.txt")).toList().toArray(new String[0]);
            long total = 0;

            regA = Integer.parseInt(lines[0].substring(lines[0].indexOf("A: ") + 3));
            regB = Integer.parseInt(lines[1].substring(lines[1].indexOf("B: ") + 3));
            regC = Integer.parseInt(lines[2].substring(lines[2].indexOf("C: ") + 3));

            int[] program = Arrays.stream(lines[4].substring(lines[4].indexOf(": ")+ 2).split(","))
                    .mapToInt(x -> Integer.parseInt(x))
                    .toArray();

            System.out.println(Arrays.toString(program));

            for (int counter = 0; counter < program.length; counter+=2) {

                int opcode = program[counter];
                int literalOperand = program[counter+1];
                int comboOperand = getComboOperandValue(program[counter+1]);

                switch (opcode) {
                    case 0:
                        regA = (int)(regA / Math.pow(2, comboOperand));
                        break;
                    case 1:
                        regB = regB ^ literalOperand;
                        break;
                    case 2:
                        regB = comboOperand % 8;
                        break;
                    case 3:
                        if(regA != 0) {
                            counter = literalOperand-2;
                            continue;
                        }
                        break;
                    case 4:
                        regB = regB ^ regC;
                        break;
                    case 5:
                        System.out.print(comboOperand % 8 + ",");
                        break;
                    case 6:
                        regB = (int)(regA / Math.pow(2, comboOperand));
                        break;
                    case 7:
                        regC = (int)(regA / Math.pow(2, comboOperand));
                        break;
                }
            }

            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getComboOperandValue(int v) {
        switch (v) {
            case 4: return regA;
            case 5: return regB;
            case 6: return regC;
            default: return v;
        }
    }
}
