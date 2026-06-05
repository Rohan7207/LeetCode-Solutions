// Problem: Number of Segments in a String
// Link: https://leetcode.com/problems/number-of-segments-in-a-string/
// Difficulty: Easy

// Approach:
// Traverse the string character by character.
// A new word (segment) starts when:
//     1. Current character is not a space.
//     2. Either:
//          - It is the first character of the string
//          OR
//          - Previous character is a space.
// Whenever such a starting position is found,
// increment count.
// Return count.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int countSegments(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' ')) {
                count++;
            }
        }

        return count;
    }
}
