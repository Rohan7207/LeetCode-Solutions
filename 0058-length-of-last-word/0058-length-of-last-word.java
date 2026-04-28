// Problem: Insert Interval
// Link: https://leetcode.com/problems/insert-interval/
// Difficulty: Medium

// Approach:
// Remove leading and trailing spaces from the string.
// Add a space at the beginning to safely handle single-word cases.
// Find the index of the last space character.
// Extract the substring after the last space, which represents the last word.
// Return the length of the last word.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        s = " " + s;
        int n = s.lastIndexOf(' ');
        String sub = s.substring(n + 1, s.length());
        int l = sub.length();
        return l;
    }
}
