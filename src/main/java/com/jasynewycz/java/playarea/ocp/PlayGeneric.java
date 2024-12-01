package com.jasynewycz.java.playarea.ocp;

import java.util.ArrayList;
import java.util.List;

public class PlayGeneric {

    public static void main(String[] args) {
        List<Integer> keywords = new ArrayList<>();
        keywords.add(2);
        printList(keywords);
    }


    public static void printList(List<? extends Number> list) {
        //list.add(3);
        for (Number x: list) {
            System.out.println(x);
        }
    }
}
