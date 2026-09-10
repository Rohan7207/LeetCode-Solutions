// Problem: First Unique Character in a String
// Link: https://leetcode.com/problems/first-unique-character-in-a-string/
// Difficulty: Easy

// Approach:
// Use frequency counting + two-pass traversal.
//
// 1. Count the frequency of every character in the string.
// 2. Traverse the string from left to right.
// 3. For each character, check its frequency.
// 4. The first character with frequency 1 is the first unique character,
//    so return its index.
// 5. If no character has frequency 1, return -1.

// Time Complexity: O(n)
// Space Complexity: O(1)
// Since the frequency array always contains 26 elements.


class Solution {
    public int firstUniqChar(String s) {
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';

            if (freq[idx] == 1) {
                return i;
            }
        }

        return -1;
    }
}
