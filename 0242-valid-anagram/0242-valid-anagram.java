// Problem: Valid Anagram
// Link: https://leetcode.com/problems/valid-anagram/
// Difficulty: Easy

// Approach:
// Use frequency counting with an integer array.
//
// 1. If the strings have different lengths, they cannot be anagrams.
// 2. Count the frequency of each character in s.
// 3. Decrease the frequency for each character in t.
// 4. If s and t are anagrams, every frequency must return to 0.
// 5. If any frequency is non-zero, the strings contain different
//    character counts.

// Time Complexity: O(n)
// Space Complexity: O(1)
// Since the frequency array always has 26 elements.


class Solution {
    public boolean isAnagram(String s, String t) {
        int[] freq = new int[26];

        if (s.length() != t.length()) {
            return false;
        }

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            freq[c - 'a']--;
        }

        for (int count : freq) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }
}
