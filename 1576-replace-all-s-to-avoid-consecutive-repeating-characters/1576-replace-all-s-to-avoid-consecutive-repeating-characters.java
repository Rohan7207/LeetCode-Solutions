// Problem: Replace All ?'s to Avoid Consecutive Repeating Characters
// Link: https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/
// Difficulty: Easy

// Approach:
// 1. Convert the string into a character array for easy modification.
//
// 2. Traverse the array from left to right.
//
// 3. Whenever we encounter '?', try characters 'a', 'b', and 'c'.
//
// 4. Choose the first character that differs from both neighbors.
//
// 5. Replace '?' with that character.
//
// 6. Return the modified character array as a string.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String modifyString(String s) {
        char[] str = s.toCharArray();
        int n = str.length;

        for (int i = 0; i < n; i++) {
            if (str[i] == '?') {
                for (char c = 'a'; c <= 'c'; c++) {
                    if ((i == 0 || str[i - 1] != c) && (i == n - 1 || str[i + 1] != c)) {
                        str[i] = c;
                        break;
                    }
                }
            }
        }
        return new String(str);
    }
}
