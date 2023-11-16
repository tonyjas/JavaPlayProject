package com.threecolts.hiring;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestHiring {

    @Test
    public void testHiring() {

        String test1 = "https://example.com";
        String test2 = "https://example.com/";

        List<String> list1 = List.of(test1, test2);

        String test3 = "https://example.com";
        String test4 = "http://example.com";

        List<String> list2 = List.of(test3, test4);

        String test5 = "https://example.com?";
        String test6 = "https://example.com";

        List<String> list3 = List.of(test5, test6);

        String test7 = "https://example.com?a=1&b=2";
        String test8 = "https://example.com?b=2&a=1";

        List<String> list4 = List.of(test7, test8);

        TonyJasynewyczHiringTestImpl tj = new TonyJasynewyczHiringTestImpl();
        assertEquals(1, tj.countUniqueUrls(list1));
        assertEquals(2, tj.countUniqueUrls(list2));
        assertEquals(1, tj.countUniqueUrls(list3));
        assertEquals(1, tj.countUniqueUrls(list4));

    }
}
