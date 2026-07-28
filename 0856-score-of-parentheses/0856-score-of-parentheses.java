// Problem: Score Of Parenthesis
// Link: https://leetcode.com/problems/score-of-parentheses/
// Difficulty: Medium

// Approach:
// Instead of computing the score for every balanced substring separately,
// observe that every valid parentheses expression is ultimately built from
// primitive pairs "()".
// A primitive pair "()" contributes a base score of 1.
// If this primitive pair is enclosed inside one pair of parentheses,
// its contribution doubles.
// Example:
// ()         -> 1
// (())       -> 2
// ((()))     -> 4
// Thus, every enclosing pair multiplies the contribution by 2.
// Maintain:
// - depth : current nesting depth.
// - score : final answer.
// Traverse the string:
// 1. If the current character is '(',
//    increase the nesting depth.
// 2. If the current character is ')',
//    first decrease the depth because one level has been closed.
//    If the previous character was '(',
//    then we have found a primitive pair "()".
//    This primitive pair is enclosed by 'depth' remaining pairs,
//    so its contribution is:
//        2^depth
//    Instead of using Math.pow(), compute it efficiently as:
//        1 << depth
//    Add this contribution to the final score.
// Continue until the entire string is processed.

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
                    score += 1 << depth; // 1 << depth is equivalent to 2^depth
                }
            }
        }

        return score;
    }
}
