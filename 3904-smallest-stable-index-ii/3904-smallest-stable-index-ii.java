// Problem: Smallest Stable Index II
// Link: https://leetcode.com/problems/smallest-stable-index-ii/?envType=daily-question&envId=2026-09-05
// Difficulty: Medium

// Approach:
// Use Suffix Minimum + Prefix Maximum.
//
// 1. Build a suffix minimum array.
//    suffixMin[i] stores the minimum value from index i to n - 1.
//
// 2. Traverse the array from left to right while maintaining
//    the maximum value seen so far.
//
// 3. At every index i:
//      max = maximum value from nums[0 ... i]
//      suffixMin[i] = minimum value from nums[i ... n - 1]
//
// 4. Check the stability condition:
//      max - suffixMin[i] <= k
//
// 5. Since we scan from left to right, the first index satisfying
//    the condition is the first stable index, so return i.
//
// 6. If no index satisfies the condition, return -1.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suffixMin = new int[n];

        int min = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < min) {
                min = nums[i];
            }

            suffixMin[i] = min;
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }

            if (max - suffixMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}
