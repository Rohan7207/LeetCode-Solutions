// Problem: Missing Number
// Link: https://leetcode.com/problems/missing-number/
// Difficulty: Easy

// Approach:
// XOR all numbers from 0 to n.
// XOR all elements present in the array.
// Numbers appearing in both groups cancel out.
// The remaining value is the missing number.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int missingNumber(int[] nums) {
        int res = 0;

        for (int i = 0; i <= nums.length; i++) {
            res ^= i;
        }

        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }

        return res;
    }
}
