// Problem: Is Subsequence
// Link: https://leetcode.com/problems/is-subsequence/
// Difficulty: Easy

// Approach:
// Use two pointers.
// i -> points to string s
// j -> points to string t
// Traverse t.
// If characters match:
//     move both pointers.
// Otherwise:
//     move only j.
// If i reaches the end of s,
// all characters of s were found
// in order inside t.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();

        if (m > n) return false;

        int i = 0;
        int j = 0;

        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }

        if (i == m)
            return true;

        return false;
    }
}
