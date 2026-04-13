// Problem : Minimum Distance to the Target Element
// Link : https://leetcode.com/problems/minimum-distance-to-the-target-element/?envType=daily-question&envId=2026-04-13
// Difficulty : Easy

// Approach:
// Start from the given index and expand in both directions.
// At each distance d, check left (start - d) and right (start + d).
// As soon as target is found, return d.
// This guarantees minimum distance due to level-wise expansion.

// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        // // Time = O(n) and Space = O(1)
        // int min = Integer.MAX_VALUE;

        // for(int i = 0; i < nums.length; i++) {
        //     if(nums[i] == target) {
        //         min = Math.min(min, Math.abs(start - i));
        //     }
        // }

        // return min;

        // Solution using the start where time = O(n) and space = O(1)

        if(nums[start] == target) return 0;

        int n = nums.length;
        int d = 1;  //It is no.of steps of start moving outward or inward

        while(start - d >= 0 || start + d < n) {
            if(start - d >= 0 && nums[start - d] == target) return d;
            if(start + d < n && nums[start + d] == target) return d;

            d++; 
        }

        return -1;
    }
}
