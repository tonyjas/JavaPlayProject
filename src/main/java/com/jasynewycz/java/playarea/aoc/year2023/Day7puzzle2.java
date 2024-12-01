package com.jasynewycz.java.playarea.aoc.year2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.*;

public class Day7puzzle2 {


    private static final long FIFTEEN_TO_THE_ELEVEN = (long)Math.pow(15, 11);
    private static final long FIFTEEN_TO_THE_TEN = (long)Math.pow(15, 10);
    private static final long FIFTEEN_TO_THE_NINE = (long)Math.pow(15, 9);
    private static final long FIFTEEN_TO_THE_EIGHT = (long)Math.pow(15, 8);
    private static final long FIFTEEN_TO_THE_SEVEN = (long)Math.pow(15, 7);
    private static final long FIFTEEN_TO_THE_SIX = (long)Math.pow(15, 6);
    private static final long FIFTEEN_TO_THE_FIVE = (long)Math.pow(15, 5);
    private static final long FIFTEEN_TO_THE_FOUR = (long)Math.pow(15, 4);
    private static final long FIFTEEN_TO_THE_THREE = (long)Math.pow(15, 3);
    private static final long FIFTEEN_TO_THE_TWO = (long)Math.pow(15, 2);

    static class Hand implements Comparable<Hand> {

        final String cards;
        final String sortedCards;
        final int score;
        long calculatedValue = -1;

        Hand(String cards, int score) {
            this.cards = cards;
            this.score = score;
            char[] temp = new char[5];
            cards.getChars(0,5, temp, 0);
            Map<Character, Integer> counts = new HashMap<>();
            for(char c : temp) {
                counts.compute(c, (x, y) -> (y != null) ? y + 1 : 1);
            }
            if(counts.containsKey('J')) {
                int jokerCount = counts.get('J');
                int highest = 0;
                char highestKey = '0';
                for(Map.Entry<Character, Integer> e : counts.entrySet()) {

                    // get highest value
                    if(e.getKey() != 'J' && e.getValue() > highest) {
                        highest = e.getValue();
                        highestKey = e.getKey();
                    }
                }
                System.out.println(Arrays.toString(temp) + "\t" + counts + "\t" + highest + "\t" + highestKey);
                for(int x = 0; x < temp.length; x++) {
                    if(temp[x] == 'J') {
                        temp[x] = highestKey;
                    }
                }
                System.out.println(Arrays.toString(temp));
            }
            Arrays.sort(temp);

            sortedCards = new String(temp).intern();

        }

        boolean is5OfAKind() {
            return sortedCards.charAt(0) == sortedCards.charAt(4);
        }

        boolean is4OfAKind() {
            return ((sortedCards.charAt(0) == sortedCards.charAt(3) || sortedCards.charAt(1) == sortedCards.charAt(4)));
        }

        boolean isFullHouse() {
            return ((sortedCards.charAt(0) == sortedCards.charAt(1) && sortedCards.charAt(3) == sortedCards.charAt(4))
                    && ((sortedCards.charAt(1) == sortedCards.charAt(2) || sortedCards.charAt(2) == sortedCards.charAt(3))));
        }

        boolean is3OfAKind() {
            return ((sortedCards.charAt(0) == sortedCards.charAt(2) || sortedCards.charAt(1) == sortedCards.charAt(3)
                    || sortedCards.charAt(2) == sortedCards.charAt(4)));
        }

        boolean is2Pair() {
            boolean onePairFound = false;
            for(int x = 0; x < 4; x++) {
                if(sortedCards.charAt(x) == sortedCards.charAt(x+1)) {
                    if(onePairFound) {
                        return true;
                    } else {
                        onePairFound = true;
                    }
                }
            }
            return false;
        }

        boolean is1Pair() {
            for(int x = 0; x < 4; x++) {
                if(sortedCards.charAt(x) == sortedCards.charAt(x+1)) {
                    return true;
                }
            }
            return false;
        }

        static int getCardValue(char card) {
            switch(card) {
                case '2', '3', '4', '5', '6', '7', '8', '9': return card-'0';
                case 'T': return 10;
                case 'J': return 1;
                case 'Q': return 12;
                case 'K': return 13;
                case 'A': return 14;
                default:
                    System.out.println("Error!");
            }
            return -1;
        }
/*
        public int getHighCardValue() {
            int value = 0;
            for(int x = 0; x < cards.length(); x++) {
                int temp = getCardValue(cards.charAt(x));
                if(temp > value) {
                    value = temp;
                }
            }
            return value;
        }
*/
        long getValue() {
            if(calculatedValue != -1) {
                return calculatedValue;
            }

            long value = 0L;
            if(is5OfAKind()) {
                value = FIFTEEN_TO_THE_ELEVEN;
            } else if(is4OfAKind()) {
                value = FIFTEEN_TO_THE_TEN;
            } else if(isFullHouse()) {
                value = FIFTEEN_TO_THE_NINE;
            } else if(is3OfAKind()) {
                value = FIFTEEN_TO_THE_EIGHT;
            } else if(is2Pair()) {
                value = FIFTEEN_TO_THE_SEVEN;
            } else if(is1Pair()) {
                value = FIFTEEN_TO_THE_SIX;
            } else {
                // highcard
                value = FIFTEEN_TO_THE_FIVE;
            }

            value += FIFTEEN_TO_THE_FOUR * getCardValue(cards.charAt(0));
            value += FIFTEEN_TO_THE_THREE * getCardValue(cards.charAt(1));
            value += FIFTEEN_TO_THE_TWO * getCardValue(cards.charAt(2));
            value += 15 * getCardValue(cards.charAt(3));
            value += getCardValue(cards.charAt(4));

            calculatedValue = value;
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Hand hand = (Hand) o;
            return Objects.equals(this.getValue(), hand.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getValue());
        }

        @Override
        public int compareTo(Hand o) {
            long temp = this.getValue() - o.getValue();
            if(temp < 0) { return -1; }
            else if(temp > 0) { return 1; }
            else { return 0; }
        }

        @Override
        public String toString() {
            return this.cards + ":" + NumberFormat.getInstance().format(this.getValue()) + ":" + this.sortedCards
                    + "\n5ofAKind: " + is5OfAKind()
                    + "\n4ofAKind: " + is4OfAKind()
                    + "\nFullHouse: " + isFullHouse()
                    + "\n3OfAKind: " + is3OfAKind()
                    + "\n2Pair: " + is2Pair()
                    + "\n1Pair: " + is1Pair()
                    + "\n\n";

        }
    }

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("./src/main/resources/com/jasynewycz/java/playarea/aoc/2023/day7puzzle1realdata.txt"));

        List<Hand> hands = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(" ");
            Hand hand = new Hand(parts[0], Integer.parseInt(parts[1]));
            hands.add(hand);
        }

        Collections.sort(hands);

        //System.out.println(hands);

        int position = 1;
        int total = 0;
        for (Hand hand : hands) {
            total += (hand.score * position);
            System.out.println(total + "\t" + position + " " + hand.score + "\t" + hand.calculatedValue + "\t" + hand.cards + "\t" + hand.sortedCards);
            position++;
        }
//        System.out.println(hands);
        System.out.println(total);
    }
}
