// Problem: Smallest Range II
// Link: https://leetcode.com/problems/smallest-range-ii/
// Difficulty: Medium

// Approach:
// First, sort the array so that the numbers are in increasing order.
// After sorting, consider every possible split between two consecutive
// elements.
// For a split after index i:
//     nums[0 ... i] | nums[i+1 ... n-1]
// Apply:
//     • +k to every element on the left.
//     • -k to every element on the right.
// After the modification:
//     The minimum element can only be:
//         min(nums[0] + k, nums[i + 1] - k)
//     The maximum element can only be:
//         max(nums[i] + k, nums[n - 1] - k)
// Compute the range for every split and keep the minimum.
// Also initialize the answer with the original range because
// performing the operations may not always reduce the score.

// Time Complexity:
//     O(n log n)
//     (Sorting dominates)
//
// Space Complexity:
//     O(1)
//     (Ignoring the sorting algorithm's internal stack)


class Solution {
    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = nums[n - 1] - nums[0];

        for (int i = 0; i < n - 1; i++) {
            int min = Math.min(nums[0] + k, nums[i + 1] - k);
            int max = Math.max(nums[n - 1] - k, nums[i] + k);

            ans = Math.min(ans, max - min);
        }

        return ans;
    }
}
