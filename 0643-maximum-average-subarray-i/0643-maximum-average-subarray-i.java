// Problem: Maximum Average Subarray I
// Link: https://leetcode.com/problems/maximum-average-subarray-i/
// Difficulty: Easy

// Approach:
// Find the maximum average subarray of
// fixed size k.
// Since k is fixed:
//     average = sum / k
// Maximizing the average is equivalent to
// maximizing the sum of a subarray of size k.
// Use Sliding Window.
// First:
//     - Calculate the sum of the first k elements
//     - This forms the initial window
// Store it as:
//     currentSum
//     maxSum
// Then slide the window one element at a time:
//     - Add the new element entering the window
//     - Remove the old element leaving the window
// Update:
//     maxSum = maximum sum seen so far
// After processing all windows:
//     Maximum Average = maxSum / k

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int currentSum = 0;
        int maxSum = 0;

        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }

        maxSum = currentSum;
        for (int i = k; i < nums.length; i++) {
            currentSum += nums[i] - nums[i - k];

            maxSum = Math.max(maxSum, currentSum);
        }

        return (double) maxSum / k;
    }
}
