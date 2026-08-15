// Problem : Longest Subsequence With Non-Zero Bitwise XOR
// Link : https://leetcode.com/problems/height-checker/
// Difficulty : Medium

// Approach:
// The goal is to find the longest subsequence whose XOR is non-zero.
//
// 1. Calculate the XOR of all elements.
//
// 2. If the XOR of the entire array is non-zero, the entire array
//    itself is a valid subsequence, so return n.
//
// 3. If the total XOR is zero, the entire array is not valid.
//    We need to remove at least one element.
//
// 4. If all elements are zero, removing elements will still give
//    XOR = 0, so no valid non-empty subsequence exists. Return 0.
//
// 5. Otherwise, there is at least one non-zero element. Removing
//    one suitable element makes the XOR non-zero, so the maximum
//    length is n - 1.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int totalXOR = 0;
        boolean allZero = true;

        for (int x : nums) {
            totalXOR ^= x;
            if (x > 0) {
                allZero = false;
            }
        }

        if (totalXOR > 0) {
            return n;
        }

        return allZero ? 0 : n - 1;
    }
}
