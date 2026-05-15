// Problem: Check if Array is Good
// Link: https://leetcode.com/problems/check-if-array-is-good/?envType=daily-question&envId=2026-05-14
// Difficulty: Easy

// Approach:
// Find the maximum value in the array.
// If array length is not equal to max value + 1,
// return false.
// Use a frequency array to count occurrences
// of every number.
// Check whether every number from 1 to n-1
// appears exactly once.
// Finally check whether number n appears twice.
// Return true if all conditions satisfy,
// otherwise return false.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public boolean isGood(int[] nums) {
        int n = 0;

        for(int num : nums) {
            n = Math.max(n, num);
        }

        if(nums.length != n + 1) return false;

        int[] freq = new int[201];

        for(int num : nums) {
            freq[num]++;
        }

        for(int i = 1; i < n; i++) {
            if(freq[i] != 1) return false;
        }

        return freq[n] == 2;
    }
}
