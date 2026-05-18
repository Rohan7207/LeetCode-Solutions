// Problem: House Robber II
// Link: https://leetcode.com/problems/house-robber-ii/
// Difficulty: Medium

// Approach:
// Since houses are arranged in circular manner,
// first and last houses cannot be robbed together.
// Convert the circular problem into two linear
// House Robber problems:
//     1. Rob houses from index 0 to n-2
//        (exclude last house)
//     2. Rob houses from index 1 to n-1
//        (exclude first house)
// For each case:
//     - Maintain two variables:
//           rob1 -> maximum till previous previous house
//           rob2 -> maximum till previous house
//     - At every house decide:
//           rob current house
//           OR
//           skip current house
// Return maximum of both cases.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int rob(int[] nums) {
        int rob1 = 0, rob2 = 0;
        int max1 = nums[0], max2 = 0;

        for(int i = 0; i < nums.length - 1; i++) {
            max1 = Math.max((rob1 + nums[i]), rob2);
            rob1 = rob2;
            rob2 = max1;
        }

        rob1 = 0;
        rob2 = 0;

        for(int i = 1; i < nums.length; i++) {
            max2 = Math.max((rob1 + nums[i]), rob2);
            rob1 = rob2;
            rob2 = max2;
        }

        return Math.max(max1, max2);
    }
}
