// Problem: Minimum Operations to Sort a String
// Link: https://leetcode.com/problems/minimum-operations-to-sort-a-string/?envType=daily-question&envId=2026-10-21
// Difficulty: Medium

// Approach:
//
// 1. First, check whether the string is already sorted in
//    non-decreasing order. If every character is <= the next
//    character, no operation is required, so return 0.
//
// 2. If the string is not sorted and its length is 2,
//    return -1 because the required operation cannot sort it.
//
// 3. Find the minimum and maximum characters in the entire string.
//
// 4. If the first character is the global minimum OR the last
//    character is the global maximum, return 1.
//    One operation can place the extreme character at its
//    required boundary and make the string sortable.
//
// 5. Otherwise, find the minimum and maximum characters among
//    the middle characters, from index 1 to n - 2.
//
// 6. If the first character is greater than every middle
//    character AND every middle character is greater than
//    the last character, return 3.
//    Both boundary characters are on the wrong sides, and the
//    middle characters lie between them, requiring three operations.
//
// 7. If none of the above conditions holds, return 2.

// Time Complexity: O(n)
// Space Complexity: O(1) 


class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // Check whether string is already sorted, if yes return 0 
        boolean isSorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                isSorted = false;
                break;
            }
        }

        if (isSorted) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch < minChar) {
                minChar = ch;
            } else if (ch > maxChar) {
                maxChar = ch;
            }
        }

        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }

        char minMid = s.charAt(1);
        char maxMid = s.charAt(1);
        for (int i = 2; i < n - 1; i++) {
            char ch = s.charAt(i);

            if (ch < minMid) {
                minMid = ch;
            } else if (ch > maxMid) {
                maxMid = ch;
            }
        }

        if (s.charAt(0) > maxMid && minMid > s.charAt(n - 1)) {
            return 3;
        }

        return 2;
    }
}
