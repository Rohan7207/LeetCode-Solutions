// Problem: Shuffle String
// Link: https://leetcode.com/problems/shuffle-string/
// Difficulty: Easy

// Approach:
//
// 1. Create a character array `res` of length n to store the
//    restored string.
//
// 2. Traverse the original string from index 0 to n - 1.
//
// 3. For every index i, `s.charAt(i)` is the character currently
//    at position i, and `indices[i]` tells us its target position.
//
// 4. Place the character at its correct position:
//
//       res[indices[i]] = s.charAt(i)
//
// 5. After processing every character, the character array
//    contains the string in the required order.
//
// 6. Convert the character array into a String and return it.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String restoreString(String s, int[] indices) {
        int n = s.length();
        char[] res = new char[n];

        for (int i = 0; i < n; i++) {
            res[indices[i]] = s.charAt(i);
        }

        return new String(res);
    }
}
