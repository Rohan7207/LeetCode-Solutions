// Problem: Backspace String Compare
// Link: https://leetcode.com/problems/backspace-string-compare/
// Difficulty: Easy

// Approach:
// The '#' character behaves like a backspace.
// Build the final version of both strings independently.
// For every character:
//     • If it is a normal character,
//       append it to the StringBuilder.
//     • If it is '#'
//       and the StringBuilder is not empty,
//       remove the last character.
// After processing both strings,
// compare their final forms.
// If they are equal,
// return true; otherwise return false.

// Time Complexity: O(n + m)
// Space Complexity: O(n + m)


class Solution {
    public boolean backspaceCompare(String s, String t) {
        return build(s).equals(build(t));
    }

    private String build(String str) {
        StringBuilder sb = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (c != '#') {
                sb.append(c); // Normal character
            } else if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1); // '#': pop the last character of sb
            }
        }

        return sb.toString();
    }
}
