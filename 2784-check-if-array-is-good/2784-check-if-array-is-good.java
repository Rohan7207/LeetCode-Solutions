// Problem: Check if Array is Good
// Link: https://leetcode.com/problems/check-if-array-is-good/
// Difficulty: Easy

// Approach:
// A special array of size n + 1 must satisfy:
// 1. The maximum value in the array is n.
// 2. Every number from 1 to n - 1 appears exactly once.
// 3. The number n appears exactly twice.
//
// Step 1:
// Find the maximum element in the array and store it in n.
// Step 2:
// A valid special array must have exactly n + 1 elements.
// If nums.length != n + 1, immediately return false.
// Step 3:
// Build a frequency array to count the occurrence of every number.
// Step 4:
// Verify that every number from 1 to n - 1 appears exactly once.
// Step 5:
// Finally, check whether the maximum value n appears exactly twice.
// If all conditions are satisfied, return true; otherwise return false.

// Time Complexity:
// O(n)
// Space Complexity:
// O(1)
// (Frequency array has fixed size.)


class Solution {
    public boolean isGood(int[] nums) {
        int n = 0;

        for (int num : nums) {
            n = Math.max(n, num);
        }

        if (nums.length != n + 1) return false;

        int[] freq = new int[201];

        for (int num : nums) {
            freq[num]++;
        }

        // Every number from 1 to n-1 appears exactly once
        for (int i = 1; i < n; i++) {
            if (freq[i] != 1) return false;
        }
        return freq[n] == 2;
    }
}
