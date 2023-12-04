package com.jasynewycz.ocp;

public class Ch4Strings {

    public static void main(String[] args) {

        String test = "TestString";
        String test1 = "TestString";
        String test2 = "TestString";
        System.out.println("test = '" + test + "'");
        System.out.println("test1 = '" + test1 + "'");
        System.out.println("test2 = '" + test2 + "'");

        String test3 = """
                Hello
                World!""";

        System.out.println("test3 = '" + test3 + "'");
        System.out.println("test3.indent(1); == '" + test3.indent(1) + "'");
        System.out.println("test3 = '" + test3 + "'");
        System.out.println("test3.stripIndent(); == '" + test3.stripIndent() + "'");

        String test4 = "1\\t2";
        System.out.println("test4 = '" + test4 + "'");
        System.out.println("test4.translateEscapes() = '" + test4.translateEscapes() + "'");


    }
}
