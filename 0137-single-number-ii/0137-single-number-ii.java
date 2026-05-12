// Problem: Single Number II
// Link: https://leetcode.com/problems/single-number-ii/
// Difficulty: Medium

// Approach:
// Use bit manipulation to track bits appearing
// once and twice.
// Maintain two variables:
//     - ones -> stores bits appearing once
//     - twos -> stores bits appearing twice
//
// For every number:
//     - Update ones using XOR and remove bits
//       already present in twos.
//     - Update twos using XOR and remove bits
//       already present in ones.
//
// Bits appearing three times are removed
// from both ones and twos automatically.
// At the end, ones contains the unique element.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;

        for(int i = 0; i < nums.length; i++) {
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }

        return ones;
    }
}
