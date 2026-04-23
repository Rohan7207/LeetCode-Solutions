// Problem: Find the index of the First Occurrence
// Link: https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
// Difficulty: Easy

// Approach:
// Use a sliding window of size equal to needle length.
// Traverse the haystack string and compare each substring
// with the needle.
// If a match is found, return the starting index.
// If no match exists, return -1.

// Time Complexity: O(n * m)
// Space Complexity: O(m)

class Solution {
    public int strStr(String haystack, String needle) {
        int j = needle.length();

        for(int i = 0; j <= haystack.length(); i++, j++){
           if(haystack.substring(i, j).equals(needle)){
                return i;
            }
        }

        return -1;
    }
}
