package com.threecolts.hiring;

import java.util.*;
import java.util.stream.Collectors;

public class TonyJasynewyczHiringTestImpl implements HiringTest {

    private class MyUrl {
        String protocol;
        String domain;
        String subdomain;
        List <String> parts;

        public MyUrl(String protocol, String domain, String subdomain, List<String> parts) {
            this.protocol = protocol;
            this.domain = domain;
            this.subdomain = subdomain;
            this.parts = parts;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyUrl myUrl = (MyUrl) o;
            return Objects.equals(protocol, myUrl.protocol) && Objects.equals(domain, myUrl.domain) && Objects.equals(subdomain, myUrl.subdomain) && Objects.equals(parts, myUrl.parts);
        }

        @Override
        public int hashCode() {
            return Objects.hash(protocol, domain, subdomain, parts);
        }

    }

    @Override
    public int countUniqueUrls(List<String> urls) {

        Set<MyUrl> uniqueUrls = new HashSet<>();

        for(String url : urls) {
            String[] parts = url.split("/");

            System.out.println("Start");
            for(String part : parts) {
                System.out.println(part);
            }
            StringBuilder subDomain = new StringBuilder();

            for(int i = 3; i < parts.length; i++) {
                subDomain.append(parts[i]);
            }
            System.out.println(subDomain.toString());
            String[] parts2 = subDomain.toString().split("\\?");


            MyUrl temp = new MyUrl(parts[0], parts[2], "", Collections.EMPTY_LIST);
            uniqueUrls.add(temp);
        }

        return uniqueUrls.size();
    }

    @Override
    public Map<String, Integer> countUniqueUrlsPerTopLevelDomain(List<String> urls) {
        List<MyUrl> domains = new ArrayList<>();

        for(String url : urls) {
            String[] parts = url.split("/");

            MyUrl temp = new MyUrl(parts[0], parts[1], "", Collections.EMPTY_LIST);
            domains.add(temp);
        }

        //return domains.stream().distinct().collect(Collectors.toMap());
        return null;
    }
}
