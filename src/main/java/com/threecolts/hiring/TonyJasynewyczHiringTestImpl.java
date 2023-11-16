package com.threecolts.hiring;

import java.util.*;


/**
 * Class to be held in a set to represent unique URL's via "equals/hashCode"
 */
public class TonyJasynewyczHiringTestImpl implements HiringTest {

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
    }

    @Override
    public Map<String, Integer> countUniqueUrlsPerTopLevelDomain(List<String> urls) {

        Map<String, Integer> map = new HashMap<>();

        for(String url : urls) {

            String domain = null;

            // get domain
            String trimmed = url.substring(0, url.indexOf(".com"));
            trimmed = trimmed.substring(trimmed.indexOf("//")+2);
            if(trimmed.contains(".")) {

                String[] temp = trimmed.split("[.]");
                domain = temp[temp.length-1] + ".com";
            } else {
                domain = trimmed + ".com";
            }

            if(map.computeIfPresent(domain, (k, v) -> Integer.valueOf(v+1)) == null) {
                map.put(domain, 1);
            }
        }
        return map;
    }
}
