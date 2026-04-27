// Problem: Maximum Subarray
// Link: https://leetcode.com/problems/maximum-subarray/
// Difficulty: Medium

// Approach:
// Use Kadane’s Algorithm to find the maximum subarray sum.
// Traverse the array and maintain a running sum.
//     - Add the current element to the running sum.
//     - Update the maximum sum if the current sum is greater.
//     - If the current sum becomes negative,
//       reset it to 0 because it cannot contribute
//       to a larger future subarray.
// Return the maximum subarray sum.

// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            maxSum = sum > maxSum ? sum : maxSum;

            sum = sum < 0 ? 0 : sum;
        }

        return maxSum;
    }
}
