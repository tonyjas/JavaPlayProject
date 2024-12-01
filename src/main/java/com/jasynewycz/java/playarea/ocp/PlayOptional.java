package com.jasynewycz.java.playarea.ocp;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class PlayOptional {

    public static class Person {
        private String name;
        private int age;

        public Person() {

        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public int getAge() {
            return age;
        }
    }


    public static void main(String[] args) {
        Person p1 = new Person();

        // Optional.ofNullable() will return an Optional object that may or may not contain a value
        String name = p1.getName()
                .map(n -> n.toString())
                .orElse("No name provided");

        System.out.println(name);

        Person p2 = new Person("John", 30);
        String name2 = p2.getName()
                .map(n -> n.toString())
                .orElse("No name provided");

        System.out.println(name2);





        Class<?> clzz = ArrayList.class;

        clzz.getSuperclass();

// Stream<Class<?>> classes =
        Stream.<Class<?>>iterate(clzz, c -> c.getSuperclass())
                .takeWhile(c -> c != null)
                .forEach(System.out::println);

    }
}