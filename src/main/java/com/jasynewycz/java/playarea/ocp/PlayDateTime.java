package com.jasynewycz.java.playarea.ocp;

import java.time.*;

public class PlayDateTime {

    public static void main(String[] args) {

        System.out.println(LocalTime.of(1, 2, 3, 4));

        // ZonedDateTime

        LocalDate date = LocalDate.of(2022, Month.NOVEMBER, 6);
        LocalTime time = LocalTime.of(1, 0);
        ZoneId tz = ZoneId.of("America/New_York");

        ZonedDateTime zdt = ZonedDateTime.of(date, time, tz);

        System.out.println(zdt);

        zdt = zdt.plusHours(1);

        System.out.println(zdt);

        System.out.println(ZoneId.systemDefault());

        ZoneId.getAvailableZoneIds().stream()
                .filter(z -> z.startsWith("Europe"))
                .sorted()
                .forEach(System.out::println);


        // Period

        Period period = Period.ofWeeks(2);

        System.out.println(period);
        System.out.println(period.plusMonths(1));
        System.out.println(period.multipliedBy(2));
        System.out.println(period.plusMonths(1).plusDays(2));

        System.out.println(Period.ofYears(1).ofMonths(2).ofWeeks(2));


        Object obj = new Object();

        if (!(obj instanceof LocalDate date2))
            return;
        else
            System.out.println(date2);


        /*if (!(obj instanceof LocalDate date3))
            System.out.println(date3);
        else
            System.out.println(date3);
*/


/*
        public class Phone {
            private int size;

            public Phone(int size) {
                size = this.size;
            }

            public static void sendHome(Phone p, int newSize) {
                p = new Phone(newSize);
                p.size = 4;
            }
            public static final void main(String... params) {
                final var phone = new Phone(3);
                sendHome(phone,7);
                System.out.print(phone.size);
            }
        }*/

    }
}
