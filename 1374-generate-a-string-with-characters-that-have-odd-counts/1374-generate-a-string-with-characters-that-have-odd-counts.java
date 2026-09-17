// Problem: Generate a String With Characters That Have Odd Counts
// Link: https://leetcode.com/problems/generate-a-string-with-characters-that-have-odd-counts/
// Difficulty: Easy

// Approach:
// Use Character Frequency Construction.
//
// 1. Create a character array of length n and fill it with 'a'.
// 2. If n is odd, all 'a' characters occur n times (odd).
// 3. If n is even, change one character to 'b'.
// 4. Now 'a' occurs n - 1 times (odd) and 'b' occurs once (odd).
// 5. Return the character array as a String.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String generateTheString(int n) {
        char[] res = new char[n];

        for (int i = 0; i < n; i++) {
            res[i] = 'a';
        }

        if (n % 2 == 0) {
            res[0] = 'b';

            return new String(res);
        }

        return new String(res);
    }
}
*/
