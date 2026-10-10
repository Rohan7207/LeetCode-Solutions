// Problem: Running Sum of 1d Array
// Link: https://leetcode.com/problems/running-sum-of-1d-array/
// Difficulty: Easy

// Approach:
// 1. Create a prefixSum array of the same length as nums.
// 2. Initialize prefixSum[0] = nums[0], because the first running sum is the first element itself.
// 3. Traverse from index 1 to n - 1.
// 4. Calculate each running sum using:
//    prefixSum[i] = prefixSum[i - 1] + nums[i].
// 5. Return prefixSum.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int[] runningSum(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        return prefixSum;
    }
}
