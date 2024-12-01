package com.jasynewycz.java.playarea.ocp;

import java.util.*;

public class PlayCollections {


    public static void main(String[] args) {

        Collection<String> c1 = new ArrayList<>();
        List<String> list1 = (List<String>) c1;

        list1.add("Test");
        list1.addAll(list1);
        System.out.println(list1.size());

        boolean removed = list1.removeIf(x -> x.equals("test"));

        System.out.println(list1 + "\n" + removed);


        Deque<String> deque = new ArrayDeque<>();

        deque.add("1");
        deque.add("2");
        deque.add("3");
        deque.add("4");

        deque.addFirst("first");
        deque.addLast("last");
        System.out.println(deque);

        System.out.println(deque.getFirst());
        System.out.println(deque);

        System.out.println(deque.getLast());
        System.out.println(deque);

        System.out.println(deque.offer("offer"));
        System.out.println(deque);

        System.out.println(deque.poll());
        System.out.println(deque);

        System.out.println(deque.pop());
        System.out.println(deque);

        System.out.println(deque.remove());
        System.out.println(deque);











    }
}
