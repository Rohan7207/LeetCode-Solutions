// Problem: Longest Continuos Increasing Subsequence
// Link: https://leetcode.com/problems/longest-continuous-increasing-subsequence/
// Difficulty: Easy

// Approach:
// We need to find the length of the
// longest continuous increasing subarray.
// Continuous means:
//     Elements must be adjacent.
// Traverse the array once and keep track of:
//     res -> current increasing streak length
//     max -> longest streak found so far
// For every adjacent pair:
//     nums[i] and nums[i+1]
// If:
//     nums[i] < nums[i+1]
// then the increasing sequence continues,
// so increase the current streak length.
// Otherwise:
//     Increasing sequence breaks,
//     reset streak length to 1.
// After every extension of the streak,
// update the maximum answer.
// Return the maximum streak length.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int res = 1;
        int max = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                res++;
                if (max < res) {
                    max = res;
                }
            } else {
                res = 1;
            }
        }

        return max;
    }
}
