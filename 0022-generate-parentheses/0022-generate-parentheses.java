// Problem: Generate Parentheses
// Link: https://leetcode.com/problems/generate-parentheses/?envType=daily-question&envId=2026-10-02
// Difficulty: Medium

// Approach: 
// 1. We need to generate all valid combinations of n pairs of parentheses.
//
// 2. At every step, we have two possible choices:
//
//    - Add '(' if open < n.
//      We cannot use more than n opening brackets.
//
//    - Add ')' if close < open.
//      A closing bracket can only be added if there is an unmatched '('.
//
// 3. Recursively explore both choices.
//
// 4. After each recursive call, remove the added bracket.
//    This is the backtracking step that lets us try another choice.
//
// 5. When the string length becomes 2 * n, we have used all brackets.
//    Add the generated string to the answer.

// Time Complexity: O(Cn * n), where Cn is the nth Catalan number
// Space Complexity: O(n) recursion depth, excluding the output


class Solution {
    public void backtrack(List<String> ans, StringBuilder curr, int open, int close, int max) {
        if (curr.length() == max * 2) {
            ans.add(curr.toString());
            return;
        }

        if (open < max) {
            curr.append("(");
            backtrack(ans, curr, open + 1, close, max);
            curr.deleteCharAt(curr.length() - 1);
        }

        if (close < open) {
            curr.append(")");
            backtrack(ans, curr, open, close + 1, max);
            curr.deleteCharAt(curr.length() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }
}
