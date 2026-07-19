// Problem: Maximum Sum Circular SubArray 
// Link: https://leetcode.com/problems/maximum-sum-circular-subarray/
// Difficulty: Medium

// Approach:
// A maximum subarray in a circular array can occur in two ways:
// 1. Normal Maximum Subarray:
//    The subarray does not wrap around the end of the array.
//    Find this using Kadane's Algorithm.
// 2. Circular Maximum Subarray:
//    The subarray wraps around the end and continues from the beginning.
//    Instead of finding this directly, observe that wrapping means
//    taking the entire array except one contiguous middle subarray.
//    Therefore:
//        Circular Sum = Total Sum - Minimum Subarray Sum
//    Find the minimum subarray sum using a modified Kadane's Algorithm
//    (replace max with min).
// During traversal:
// • Compute the total sum of the array.
// • Compute the maximum subarray sum (Kadane).
// • Compute the minimum subarray sum (Reverse Kadane).
// Edge Case:
// If all elements are negative, the minimum subarray becomes the
// entire array, making:
//      Total Sum - Minimum Sum = 0
// This corresponds to choosing an empty subarray, which is not allowed.
// In this case, simply return the normal maximum subarray sum.
// Otherwise, the answer is the maximum of:
//      maxSubarraySum
//      totalSum - minSubarraySum

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = nums[0];
        int currMax = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            totalSum += nums[i];
            currMax = Math.max(nums[i], currMax + nums[i]);
            maxSum = Math.max(maxSum, currMax);
        }

        int currMin = nums[0];
        int minSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currMin = Math.min(nums[i], currMin + nums[i]);
            minSum = Math.min(minSum, currMin);
        }

        if (maxSum < 0)
            return maxSum;

        return Math.max(maxSum, totalSum - minSum);
    }
}
