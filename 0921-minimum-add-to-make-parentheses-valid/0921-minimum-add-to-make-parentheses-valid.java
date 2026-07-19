// Problem: Minimum Add to Make Parentheses Valid
// Link: https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
// Difficulty: Medium

// Approach:
// The goal is to find the minimum number of parentheses
// that must be inserted to make the string valid.
// Maintain two counters:
// • open:
//   Stores the number of unmatched opening parentheses '('.
// • missingOpen:
//   Stores the number of opening parentheses that need
//   to be inserted for unmatched closing parentheses ')'.
// Traverse the string once:
// • If the current character is '(',
//   increment open because it is waiting
//   for a matching ')'.
// • If the current character is ')':
//      - If there is an unmatched '(' (open > 0),
//        match it by decrementing open.
//      - Otherwise, this ')' has no matching '(',
//        so increment missingOpen.
// After processing the entire string:
// • Every remaining unmatched '(' requires one ')'
//   to make the string valid.
// • Every unmatched ')' counted in missingOpen
//   requires one '('.
// Therefore, the minimum insertions required are:
//      unmatched '(' + unmatched ')'
// i.e.
//      open + missingOpen

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;
        int missingOpen = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    missingOpen++;
                }
            }
        }

        return open + missingOpen;
    }
}
