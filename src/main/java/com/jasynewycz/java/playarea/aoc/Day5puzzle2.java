package com.jasynewycz.java.playarea.aoc;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day5puzzle2 {

    static class RangeMapper {
        final long lowKey;
        final long highKey;
        final long offset;

        public RangeMapper(long lowKey, long highKey, long offset) {
            this.lowKey = lowKey;
            this.highKey = highKey;
            this.offset = offset;
        }

        public RangeMapper(String[] data) {
            lowKey = Long.parseLong(data[1]);
            highKey = Long.parseLong(data[2])+lowKey-1;
            offset = Long.parseLong(data[0]) - Long.parseLong(data[1]);
        }

        public boolean isInRange(long value) {
            if(value >= lowKey && value <= highKey) {
                return true;
            }
            return false;
        }

        public long mapValue(long value) {
            if(isInRange(value)) {
                return value + offset;
            } else {
                return -1L;
            }
        }

        @Override
        public String toString() {
            return "[lowkey: " + lowKey + ", highkey: " + highKey + ", offset: " + offset + "]";
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/day5puzzle1realdata.txt"));

        String[] preSeeds = lines.get(0).substring(7).split(" ");

        List<RangeMapper> seedToSoilList = new ArrayList<>();
        List<RangeMapper> soilToFertilizerList = new ArrayList<>();
        List<RangeMapper> fertilizerToWaterList = new ArrayList<>();
        List<RangeMapper> waterToLightList = new ArrayList<>();
        List<RangeMapper> lightToTempList = new ArrayList<>();
        List<RangeMapper> tempToHumidityList = new ArrayList<>();
        List<RangeMapper> humidityToLocationList = new ArrayList<>();


        int x = 3;

        while(!lines.get(x).isEmpty()) {
            // seed to soil map
            seedToSoilList.add(new RangeMapper(lines.get(x).split(" ")));
            x++;
        }
        x+=2;
        while(!lines.get(x).isEmpty()) {
            // seed to soil map
            soilToFertilizerList.add(new RangeMapper(lines.get(x).split(" ")));
            x++;
        }
        x+=2;
        while(!lines.get(x).isEmpty()) {
            // seed to soil map
            fertilizerToWaterList.add(new RangeMapper(lines.get(x).split(" ")));
            x++;
        }
        x+=2;
        while(!lines.get(x).isEmpty()) {
            // seed to soil map
            waterToLightList.add(new RangeMapper(lines.get(x).split(" ")));
            x++;
        }
        x+=2;
        while(!lines.get(x).isEmpty()) {
            // seed to soil map
            lightToTempList.add(new RangeMapper(lines.get(x).split(" ")));
            x++;
        }
        x+=2;
        while(!lines.get(x).isEmpty()) {
            // seed to soil map
            tempToHumidityList.add(new RangeMapper(lines.get(x).split(" ")));
            x++;
        }
        x+=2;
        while(x < lines.size()) {
            // seed to soil map
            humidityToLocationList.add(new RangeMapper(lines.get(x).split(" ")));
            x++;
        }


        System.out.println(seedToSoilList);
        System.out.println(soilToFertilizerList);
        System.out.println(fertilizerToWaterList);
        System.out.println(waterToLightList);
        System.out.println(lightToTempList);
        System.out.println(tempToHumidityList);
        System.out.println(humidityToLocationList);

        long lowest = Long.MAX_VALUE;

        for(int i = 0; i < preSeeds.length; i+=2) {

            long seedValue = Long.parseLong(preSeeds[i]);
            long maxValue = Long.parseLong(preSeeds[i+1]) + seedValue -1;

            System.out.println(seedValue);
            System.out.println(maxValue);

            for(; seedValue < maxValue; seedValue++) {
                long value = seedValue;
                value = getMappedValue(value, seedToSoilList);
                value = getMappedValue(value, soilToFertilizerList);
                value = getMappedValue(value, fertilizerToWaterList);
                value = getMappedValue(value, waterToLightList);
                value = getMappedValue(value, lightToTempList);
                value = getMappedValue(value, tempToHumidityList);
                value = getMappedValue(value, humidityToLocationList);

                lowest = Math.min(value, lowest);
            }
        }

        System.out.println("Answer: " + lowest);
    }

    private static long getMappedValue(long value, List<RangeMapper> list) {
        for(RangeMapper mapper : list) {

            if(mapper.mapValue(value) != -1) {
                value = mapper.mapValue(value);
                break;
            }
        }
        return value;
    }
}
