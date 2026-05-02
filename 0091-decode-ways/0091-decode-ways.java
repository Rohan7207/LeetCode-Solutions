// Problem: Decode Ways
// Link: https://leetcode.com/problems/decode-ways/
// Difficulty: Medium

// Approach:
// Use dynamic programming with two variables
// to store the number of decoding ways.
// If the string starts with '0',
// return 0 because it is invalid.
// Traverse the string from index 1:
//     - If current character is not '0',
//       current position can use previous count.
//     - Form a two-digit number using
//       previous and current characters.
//     - If the value lies between 10 and 26,
//       add ways from two positions back.
// Update variables for next iteration.
// Return the total decoding ways.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;

        int first = 1;
        int second = 1;

        for (int i = 1; i < s.length(); i++) {
            int current = 0;

            if (s.charAt(i) != '0') {
                current = first;
            }

            int val = Integer.parseInt(s.substring(i - 1, i + 1));
            if (val >= 10 && val <= 26) {
                current = current + second;
            }

            second = first;
            first = current;
        }

        return first;
    }
}
