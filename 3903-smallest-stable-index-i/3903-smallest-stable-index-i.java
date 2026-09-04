// Problem: Smallest Stable Index I
// Link: https://leetcode.com/problems/smallest-stable-index-i/?envType=daily-question&envId=2026-09-04
// Difficulty: Easy

// Approach:
// Use Suffix Minimum + Prefix Maximum.
//
// 1. Build a suffix minimum array:
//      prefixMin[i] = minimum element from index i to n - 1.
//
// 2. Traverse the array from left to right while maintaining
//    the maximum element seen so far.
//
// 3. At index i:
//      max = maximum(nums[0 ... i])
//      prefixMin[i] = minimum(nums[i ... n-1])
//
// 4. If:
//      max - prefixMin[i] <= k
//    then index i is stable, so return i.
//
// 5. If no index satisfies the condition, return -1.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMin = new int[n];
        prefixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            prefixMin[i] = Math.min(prefixMin[i + 1], nums[i]);
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);

            if (max - prefixMin[i] <= k) {
                return i;
            }
        }

        return -1;
    }
}
