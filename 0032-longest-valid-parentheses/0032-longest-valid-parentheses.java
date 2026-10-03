// Problem: Longest Valid Parentheses
// Link: https://leetcode.com/problems/longest-valid-parentheses/?envType=daily-question&envId=2026-10-03
// Difficulty: Hard

// Approach:
// 1. Instead of using a stack, keep counts of '(' and ')'.
//
// 2. Traverse from LEFT → RIGHT.
//    - Increment open for '('.
//    - Increment close for ')'.
//
// 3. When open == close, we have a valid balanced substring.
//    Its length is open + close.
//
// 4. If close > open, there are more ')' than '('.
//    This can never become valid by extending this substring,
//    so reset both counters.
//
// 5. Why one direction is not enough:
//    Consider "(()".
//    Left → right gives open > close, so we never count the final
//    valid part.
//
// 6. Therefore traverse RIGHT → LEFT as well.
//    Now the symmetric invalid case (more '(' than ')') can be
//    detected and reset.
//
// 7. During the right-to-left traversal:
//    - If open == close → valid substring.
//    - If open > close → impossible to balance this part,
//      so reset.
//
// 8. Take the maximum length found in both traversals.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int longestValidParentheses(String s) {
        int res = 0;
        int n = s.length();
        int open = 0, close = 0;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                res = Math.max(res, open + close);
            } else if (close > open) {
                open = close = 0;
            }
        }

        open = 0;
        close = 0;
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                res = Math.max(res, open + close);
            } else if (open > close) {
                open = close = 0;
            }
        }

        return res;
    }
}
