// Problem: Number of Strings That Appear as Substrings in Word
// Link: https://leetcode.com/problems/number-of-strings-that-appear-as-substrings-in-word/?envType=daily-question&envId=2026-06-29
// Difficulty: Easy

// Approach:
// Count how many strings from patterns appear as substrings
// inside word.
// Step 1:
// Traverse every pattern string.
// Step 2:
// For each pattern:
//     Use word.contains(pattern)
//     If pattern exists inside word:
//          increase the answer.
// Step 3:
// Return the count.
// Since each pattern is checked independently,
// we don't need extra data structures.

// Time Complexity: O(n * m)
//     n = number of patterns
//     m = length of word
//
// Space Complexity: O(1)


class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int res = 0;

        for (String pattern : patterns) {
            if (word.contains(pattern)) {
                res++;
            }
        }

        return res;
    }
}
