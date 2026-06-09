// Problem: Maximum Total Subarray Value I
// Link: https://leetcode.com/problems/maximum-total-subarray-value-i/?envType=daily-question&envId=2026-06-09
// Difficulty: Medium

// Approach:
// The value of a subarray is:
//     maximum element - minimum element
// Since the same subarray can be chosen
// multiple times, we only need to find the
// maximum possible value of a single subarray.
// The maximum possible value is obtained by
// taking a subarray that contains:
//     - the global maximum element
//     - the global minimum element
// The whole array always satisfies this.
// Therefore:
//     Best Subarray Value
//         = globalMax - globalMin
// Since we can choose the same optimal
// subarray exactly k times:
//     Answer
//         = k * (globalMax - globalMin)
// Traverse the array once to find:
//     - global maximum
//     - global minimum
// Then compute the final answer.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        return (long) k * (max - min);
    }
}
