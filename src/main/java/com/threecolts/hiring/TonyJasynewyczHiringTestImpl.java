package com.threecolts.hiring;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class TonyJasynewyczHiringTestImpl implements HiringTest {



    /**
     * Class to be held in a set to represent unique URL's via "equals/hashCode"
     */
    private class MyUrl {
        String domain;
        List <String> params;

        public MyUrl(String domain, List<String> params) {
            this.domain = domain;
            this.params = params;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyUrl myUrl = (MyUrl) o;
            return Objects.equals(domain, myUrl.domain) && Objects.equals(params, myUrl.params);
        }

        @Override
        public int hashCode() {
            return Objects.hash(domain, params);
        }
    }

    @Override
    public int countUniqueUrls(List<String> urls) {

        return urls.stream()
                .map(url -> {
                    String[] paramParts = null;

                    String paramsTemp = null;
                    String urlOnly = null;

                    if(url.contains("?")) {
                        String[] parts = url.split("[?]");
                        urlOnly = parts[0];
                        if(parts.length > 1) {
                            paramsTemp = parts[1];
                            paramParts = paramsTemp.split("&");
                            Arrays.sort(paramParts);
                        }
                    } else {
                        urlOnly = url;
                    }

                    // if we have a trailing / then remove it
                    if(urlOnly.endsWith("/")) {
                        urlOnly = urlOnly.substring(0, urlOnly.length() - 1);
                    }

                    return new MyUrl(urlOnly, (paramParts != null && paramParts.length > 0) ? Arrays.asList(paramParts) : null);

                })
                .distinct()
                .collect(Collectors.collectingAndThen(Collectors.counting(), Long::intValue));

        /*
        Set<MyUrl> uniqueUrls = new HashSet<>();

        for(String url : urls) {

            String[] paramParts = null;

            String paramsTemp = null;
            String urlOnly = null;

            if(url.contains("?")) {
                String[] parts = url.split("[?]");
                urlOnly = parts[0];
                if(parts.length > 1) {
                    paramsTemp = parts[1];
                    paramParts = paramsTemp.split("&");
                    Arrays.sort(paramParts);
                }
            } else {
                urlOnly = url;
            }

            // if we have a trailing / then remove it
            if(urlOnly.endsWith("/")) {
                urlOnly = urlOnly.substring(0, urlOnly.length() - 1);
            }

            MyUrl temp = new MyUrl(urlOnly, (paramParts != null && paramParts.length > 0) ? Arrays.asList(paramParts) : null);
            uniqueUrls.add(temp);
        }

        return uniqueUrls.size();
        */

    }

    @Override
    public Map<String, Integer> countUniqueUrlsPerTopLevelDomain(List<String> urls) {

        return urls.stream()
                .map(url -> url.substring(url.indexOf("//")+2, url.indexOf(".com")))
                .map(url -> {
                    String domain = null;
                    if(url.contains(".")) {
                        String[] temp = url.split("[.]");
                        domain = temp[temp.length-1];
                    } else {
                        domain = url;
                    }
                    return domain + ".com";
                })
                .collect(
                        Collectors.groupingBy(Function.identity(),
                                Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
    }
}
