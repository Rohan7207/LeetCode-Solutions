// Problem: Single Number
// Link: https://leetcode.com/problems/single-number/
// Difficulty: Easy

// Approach:
// Use XOR operation to find the unique element.
// Traverse the array and XOR every number with result.
// Properties of XOR:
//     - a ^ a = 0
//     - a ^ 0 = a
// Since duplicate numbers cancel each other out,
// only the single occurring element remains.
// Return the final result.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }

        return res;
    }
}
