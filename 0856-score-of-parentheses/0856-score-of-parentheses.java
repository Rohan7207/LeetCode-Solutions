// Problem: Score of Parentheses
// Link: https://leetcode.com/problems/score-of-parentheses/?envType=daily-question&envId=2026-10-05
// Difficulty: Medium

// Approach:
// 1. Every primitive "()" contributes 1 at its current nesting depth.
// 2. If "()" is nested inside d pairs of parentheses, its value becomes 2^d.
// 3. Track the current nesting depth using `depth`.
// 4. When we see '(' → increase depth.
// 5. When we see ')' → decrease depth first.
// 6. If the previous character is '(' → we just closed a primitive "()".
// 7. Its contribution is 2^depth because depth is now the outer nesting level.
// 8. Add this contribution to score.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int scoreOfParentheses(String s) {
        int score = 0;
        int depth = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
                if (s.charAt(i - 1) == '(') {
                    score += 1 << depth; 
                }
            }
        }

        return score;
    }
}
