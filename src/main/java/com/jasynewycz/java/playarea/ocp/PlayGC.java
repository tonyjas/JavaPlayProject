package com.jasynewycz.java.playarea.ocp;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class PlayGC {

    public static void main(String[] args) {
        List<GarbageCollectorMXBean> list = ManagementFactory.getGarbageCollectorMXBeans();

        for(GarbageCollectorMXBean bean : list) {
            System.out.println("Name: " + bean.getName());
            System.out.println("No. of collections: " + bean.getCollectionCount());
            System.out.println("Collection time: " + bean.getCollectionTime());
            System.out.println("Pool names");

            for(String name : bean.getMemoryPoolNames()) {
                System.out.println("\t" + name);
            }

            System.out.println();
        }
    }
}
