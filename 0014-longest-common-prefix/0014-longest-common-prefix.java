// Problem: Longest Common Prefix
// Link: https://leetcode.com/problems/longest-common-prefix/
// Difficulty: Easy

// Approach:
// Take the first string as the initial prefix.
// Compare it with each string in the array.
// While the current string does not start with the prefix,
// Keep reducing the prefix by removing the last character.
// Repeat until all strings are processed.
// Return the final prefix.

// Time Complexity: O(n * m)
// Space Complexity: O(1)

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";

        String prefix = strs[0];

        for(int i = 1; i < strs.length; i++) {
            while(strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);

                if(prefix.isEmpty()) return "";
            }
        }

        return prefix;
    }
}
