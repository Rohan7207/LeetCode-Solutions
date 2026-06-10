// Problem: Permutation in String
// Link: https://leetcode.com/problems/permutation-in-string/
// Difficulty: Medium

// Approach:
// Check if s2 contains any permutation of s1.
// A permutation means the order can be different,
// but character frequency must be the same.
// Use sliding window of size s1.length().
// Maintain two frequency arrays:
//     s1Map -> frequency of characters in s1
//     s2Map -> frequency of characters in current window of s2
// First:
//     - Store frequency of all characters in s1
//     - Store frequency of first window in s2
// Then slide the window through s2:
//     - Compare both frequency arrays
//     - If they match:
//           current window is a permutation of s1
//     - Add the new character entering the window
//     - Remove the old character leaving the window
// After loop, check the last window also.

// Time Complexity: O(26 * n) ≈ O(n)
// Space Complexity: O(1)


class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;

        int[] s1Map = new int[26];
        int[] s2Map = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1Map[s1.charAt(i) - 'a']++;
            s2Map[s2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (matches(s1Map, s2Map))
                return true;

            s2Map[s2.charAt(i + s1.length()) - 'a']++;
            s2Map[s2.charAt(i) - 'a']--; 
        }

        return matches(s1Map, s2Map);
    }

    private boolean matches(int[] s1Map, int[] s2Map) {
        for (int i = 0; i < 26; i++) {
            if (s1Map[i] != s2Map[i]) {
                return false;
            }
        }
        return true;
    }
}
