// Problem: House Robber
// Link: https://leetcode.com/problems/house-robber/
// Difficulty: Medium

// Approach:
// Solve the House Robber problem using dynamic programming
// with space optimization.
// Maintain two variables:
//     - rob1 -> maximum money robbed till previous previous house
//     - rob2 -> maximum money robbed till previous house
// For every house:
//     - Two choices:
//           1. Rob current house:
//                  rob1 + current house money
//           2. Skip current house:
//                  rob2
//     - Take maximum of both choices
// After computing current maximum:
//     - Move rob1 to rob2
//     - Move rob2 to current maximum
// This ensures adjacent houses are never robbed together.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int rob(int[] nums) {
        int rob1 = 0;
        int rob2 = 0;
        int max = 0;

        for(int i = 0; i < nums.length; i++) {
            max = Math.max(rob1 + nums[i], rob2);
            rob1 = rob2;
            rob2 = max;
        }

        return max;
    }
}
