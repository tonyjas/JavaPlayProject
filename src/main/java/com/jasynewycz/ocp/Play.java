package com.jasynewycz.ocp;

public class Play {

    public static void main(final String[] things) {

        int pig = (short)4;
        pig = pig++;
        System.out.println(pig);

        Play play = new Play();

        int[] weather = new int[] {1,2,3};

        for(int i = 0; i<weather.length; ++i) {
            System.out.println(weather[i]);
        }
    }




    public void Play() {
        System.out.println("Not a Constructor as we have a return statement!");
    }


}
