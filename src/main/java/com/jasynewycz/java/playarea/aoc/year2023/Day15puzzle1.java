package com.jasynewycz.java.playarea.aoc.year2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day15puzzle1 {


    static class Lens {
        String label;
        int focalLength;

        public Lens(String label, int focalLength) {
            this.label = label;
            this.focalLength = focalLength;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getFocalLength() {
            return focalLength;
        }

        public void setFocalLength(int focalLength) {
            this.focalLength = focalLength;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Lens lens = (Lens) o;
            return Objects.equals(label, lens.label);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label);
        }

        @Override
        public String toString() {
            return "Lens{" +
                    "label='" + label + '\'' +
                    ", focalLength=" + focalLength +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {

        String text = Files.readString(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2023/day15puzzle1realdata.txt"));

        String[] parts = text.split(",");

        long total = 0;

        Map<Integer, ArrayList<Lens>> map = new HashMap<>();

        for(String part : parts) {

            String[] temp = part.split("[=-]");

            int currentValue = 0;
            for(int x = 0; x < temp[0].length(); x++) {
                currentValue += part.charAt(x);
                currentValue *= 17;
                currentValue %= 256;
            }

            // day 2 functionality
            if(part.contains("=")) {

                if(!map.containsKey(currentValue)) {
                    ArrayList<Lens> list = new ArrayList<>();
                    list.add(new Lens(temp[0], Integer.parseInt(temp[1])));
                    map.put(currentValue, list);
                } else {
                    ArrayList<Lens> list = map.get(currentValue);
                    Optional<Lens> lens = list.stream().filter(x -> x.getLabel().equals(temp[0])).findAny();
                    if(lens.isPresent()) {
                        list.forEach(x -> {
                            if(x.getLabel().equals(temp[0])) {
                                x.setFocalLength(Integer.parseInt(temp[1]));
                            }
                        });
                    } else {
                        list.add(new Lens(temp[0], Integer.parseInt(temp[1])));
                    }
                }

            } else if(part.contains("-")) {
                if(map.containsKey(currentValue)) {
                    ArrayList<Lens> values = map.get(currentValue);
                    if(values == null) {
                        continue;
                    }
                    ArrayList<Lens> t = (ArrayList<Lens>) values.stream().filter(x -> !x.getLabel().equals(temp[0])).collect(Collectors.toList());
                    map.put(currentValue, t);
                }
            }

        }

        System.out.println(map);


        for(Map.Entry<Integer, ArrayList<Lens>> lensEntry : map.entrySet()) {
            if(lensEntry != null && lensEntry.getValue() != null && lensEntry.getValue().size() > 0) {
                ArrayList<Lens> boxContents = lensEntry.getValue();
                int boxIndex = lensEntry.getKey() + 1;
                for(int x = 0; x < boxContents.size(); x++) {

                    total += (boxIndex * (x+1) * boxContents.get(x).focalLength);
                }
            }
        }

        System.out.println(total);
    }
}
