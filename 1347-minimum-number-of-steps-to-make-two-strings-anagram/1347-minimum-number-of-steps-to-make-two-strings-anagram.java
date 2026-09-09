// Problem: Minimum Number of Steps to Make Two Strings Anagram
// Link: https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
// Difficulty: Medium

// Approach:
// Use Frequency Counting.
//
// 1. Count the frequency of every character in s.
// 2. Subtract the frequency of every character in t.
// 3. A positive frequency means s contains extra copies of that
//    character that must be removed.
// 4. Add all positive frequencies to get the minimum number of steps.
//
// Why does this work?
// Since we can delete characters from s, only the characters that
// appear more times in s than in t need to be removed.

// Time Complexity: O(n + m)
// Space Complexity: O(1)  // only 26 characters


class Solution {
    public int minSteps(String s, String t) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            freq[c - 'a']--;
        }

        int steps = 0;
        for (int count : freq) {
            if (count > 0) {
                steps += count;
            }
        }


        return steps;
    }
}
