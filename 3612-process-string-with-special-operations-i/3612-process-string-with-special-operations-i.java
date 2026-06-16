// Problem: Process String with Special Operations I
// Link: https://leetcode.com/problems/process-string-with-special-operations-i/?envType=daily-question&envId=2026-06-16
// Difficulty: Medium

// Approach:
// Process the string character by character
// and build the answer using StringBuilder.
// Rules:
// Normal character:
//     Append it to StringBuilder.
// '#':
//     Duplicate the current string.
//     Example:
//         "ab"
//          ↓
//         "abab"
// '%':
//     Reverse the entire string.
//     Example:
//         "abc"
//          ↓
//         "cba"
// '*':
//     Delete the last character
//     if the string is not empty.
// Traverse every character in s
// and perform the corresponding operation.
// Finally return the generated string.

// Time Complexity:
//     O(n + operations)
// Space Complexity:
//     O(result length)


class Solution {
    public String processStr(String s) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '#') {
                str.append(str.toString());
            } else if (ch == '%') {
                str.reverse();
            } else if (ch == '*') {
                if (str.length() > 0) {
                    str.deleteCharAt(str.length() - 1);
                }
            } else {
                str.append(ch);
            }
        }

        return str.toString();
    }
}
