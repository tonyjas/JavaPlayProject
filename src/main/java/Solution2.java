import java.util.*;

import static java.util.Arrays.*;

class Solution2 {

    public static void main(String[] args) {

        Solution2 sol = new Solution2();
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(sol.findMedianSortedArrays(nums1, nums2));

        int[] searchArray = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        System.out.println(sol.binarySearch(searchArray, -1));
        System.out.println(sol.binarySearch(searchArray, 0));
        System.out.println(sol.binarySearch(searchArray, 1));
        System.out.println(sol.binarySearch(searchArray, 2));
        System.out.println(sol.binarySearch(searchArray, 3));
        System.out.println(sol.binarySearch(searchArray, 4));
        System.out.println(sol.binarySearch(searchArray, 5));
        System.out.println(sol.binarySearch(searchArray, 6));
        System.out.println(sol.binarySearch(searchArray, 7));
        System.out.println(sol.binarySearch(searchArray, 8));
        System.out.println(sol.binarySearch(searchArray, 9));
        System.out.println(sol.binarySearch(searchArray, 10));
        System.out.println(sol.binarySearch(searchArray, 11));
        System.out.println(sol.binarySearch(searchArray, 12));
        System.out.println(sol.binarySearch(searchArray, 13));
        System.out.println(sol.binarySearch(searchArray, 14));
        System.out.println(sol.binarySearch(searchArray, 15));
        System.out.println(sol.binarySearch(searchArray, 16));


       // System.out.println(sol.reductionOperations(redOps));

        System.out.println(sol.longestPalindrome("cbbd"));
        System.out.println(sol.longestPalindrome("bb"));
        System.out.println(sol.longestPalindrome(""));
        System.out.println(sol.longestPalindrome("b"));

        System.out.println(sol.longestPalindrome2("cbbd"));
        System.out.println(sol.longestPalindrome2("bb"));
        System.out.println(sol.longestPalindrome2(""));
        System.out.println(sol.longestPalindrome2("b"));
    }

    public String longestPalindrome2(String s) {
        return "";

        /*int longest = 1;

        for(int x = 0; x < s.length(); x++) {
            // start at position 0
            int y = x;
            while(x+1 < s.length() && y >= 0) {
                if (s.charAt(x) != s.charAt(y)) {
                    break;
                }
                if ()
                    y--;
                x++;
            }
        }
*/
    }

    public String longestPalindrome(String s) {

        for(int length = s.length(); length >= 0 ; length--) {
            for(int x = 0; x <= s.length()-length; x++) {
                String temp = s.substring(x, length+x);
                if(isPalindrome(temp)) {
                    return temp;
                }
            }
        }

        return "";
    }

    boolean isPalindrome(String s) {
        for(int x = 0, y = s.length()-1; x <= y; x++, y--) {
            if(s.charAt(x) != s.charAt(y)) {
                return false;
            }
        }
        return true;
    }
/*
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // brute force merge them
        int[] merged = new int[nums1.length+nums2.length];
        for(int x = 0, y = 0; x < nums1.length || y < nums2.length;) {
            if(x >= nums1.length) {
                merged[x+y] = nums2[y++];
                continue;
            }
            if(y >= nums2.length) {
                merged[x+y] = nums1[x++];
                continue;
            }
            if(nums1[x] >= nums2[y]) {
                merged[x+y] = nums2[y];
                y++;
            } else {
                merged[x+y] = nums1[x];
                x++;
            }
        }
        System.out.println();

        if(merged.length % 2 == 0) {
            int midPoint = merged.length / 2;
            return ((double)merged[midPoint-1] + (double)merged[midPoint]) / 2.0;
        } else {
            return (double)merged[merged.length / 2];
        }
    }
*/

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;
        boolean isOdd = (m+n)%2==1;

        int nums1MidPoint = nums1.length/2;
        int num1sValue = nums1[nums1MidPoint];
        int nums2MidPoint = nums2.length/2;
        int num2sValue = nums2[nums2MidPoint];

        System.out.println(nums1MidPoint);
        System.out.println(num1sValue);
        System.out.println(nums2MidPoint);
        System.out.println(num2sValue);

        if(num1sValue == num2sValue) {

        }

        return 0.0;
    }


    public int binarySearch(int[] nums, int find){
        return binarySearch(nums, find, 0, nums.length-1);
    }

    public int binarySearch(int[] nums, int find, int start, int end) {

        int mid = start + (end - start + 1) / 2;
        if(mid > end || mid < 0) {
            return -1;
        }
        if(nums[mid] == find) {
            return mid;
        }
        if(nums[mid] <= find) {
            return binarySearch(nums, find, mid+1, end);
        } else {
            return binarySearch(nums, find, start, mid-1);
        }
    }


    public int reductionOperations(int[] nums) {

        Arrays.sort(nums);

        System.out.println(Arrays.toString(nums));
        int opCount = 0;
        int x = nums.length-1, y = nums.length-1;

        while(x >= 0 && y > 0) {
            if(nums[x] == nums[--y]) {
                continue;
            } else {

                while(x > y) {
                    nums[x--] = nums[y];
                    opCount++;
                }
                x = nums.length-1;
            }
        }
        System.out.println(Arrays.toString(nums));
        return opCount;
    }
}