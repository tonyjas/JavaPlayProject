package com.threecolts.hiring;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestHiring {

    @Test
    public void test1Hiring() {

        String test1 = "https://example.com";
        String test2 = "https://example.com/";

        List<String> list1 = List.of(test1, test2);

        TonyJasynewyczHiringTestImpl tj = new TonyJasynewyczHiringTestImpl();
        assertEquals(1, tj.countUniqueUrls(list1));

    }

    @Test
    public void test2Hiring() {

        String test3 = "https://example.com";
        String test4 = "http://example.com";

        List<String> list2 = List.of(test3, test4);

        TonyJasynewyczHiringTestImpl tj = new TonyJasynewyczHiringTestImpl();
        assertEquals(2, tj.countUniqueUrls(list2));
    }

    @Test
    public void test3Hiring() {

        String test5 = "https://example.com?";
        String test6 = "https://example.com";

        List<String> list3 = List.of(test5, test6);

        TonyJasynewyczHiringTestImpl tj = new TonyJasynewyczHiringTestImpl();
        assertEquals(1, tj.countUniqueUrls(list3));
    }

    @Test
    public void test4Hiring() {

        String test7 = "https://example.com?a=1&b=2";
        String test8 = "https://example.com?b=2&a=1";

        List<String> list4 = List.of(test7, test8);

        TonyJasynewyczHiringTestImpl tj = new TonyJasynewyczHiringTestImpl();
        assertEquals(1, tj.countUniqueUrls(list4));
    }

    @Test
    public void testDomain1() {

        String test1 = "https://example.com";
        String test2 = "https://subdomain.example.com";
        String test3 = "https://subdomain.example2.com";

        List<String> list = List.of(test1, test2, test3);

        TonyJasynewyczHiringTestImpl tj = new TonyJasynewyczHiringTestImpl();

        assertEquals(2, tj.countUniqueUrlsPerTopLevelDomain(list).get("example.com").intValue());
        assertEquals(1, tj.countUniqueUrlsPerTopLevelDomain(list).get("example2.com").intValue());
    }
}