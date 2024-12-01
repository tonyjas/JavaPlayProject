package com.jasynewycz.java.playarea.ocp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class PlayIOStreams {

    public static void main(String[] args) throws IOException {

        Files.list(Path.of(".")).forEach(System.out::println);

        try (
                BufferedReader br = new BufferedReader(
                        new FileReader(new File("./src/main/resources/com/jasynewycz/java/playarea/test.txt"))
                );
                PrintWriter pw = new PrintWriter(
                        new FileWriter(new File("./src/main/resources/com/jasynewycz/java/playarea/destination.txt"))
                )
                ) {

            String line;

            while ((line = br.readLine()) != null) {
                pw.println(line);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
