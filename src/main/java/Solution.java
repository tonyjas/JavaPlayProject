import java.util.HashSet;
import java.util.Set;

class Solution {

    public static void main(String[] args) {

        Solution sol = new Solution();
        System.out.println(sol.lengthOfLongestSubstring("aab"));
    }

    public int lengthOfLongestSubstring(String s) {
        int maxCount = 0;
        int startCurrent = 0;

        Set<Character> tempStore = new HashSet<>();

        if(s.length() <= 1) {
            return s.length();
        }

        for(int x = 0; x < s.length(); x++) {

            if(!tempStore.add(s.charAt(x))) {
                maxCount = Math.max(maxCount, x - startCurrent);
                x = startCurrent++; // go back to 1 beyond the start
                tempStore = new HashSet<>();
            }
        }

        maxCount = Math.max(maxCount, s.length() - startCurrent);

        if(maxCount == 0) {
            return s.length();
        }

        return maxCount;
    }
}