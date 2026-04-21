// Problem: Generate Parentheses
// Link: https://leetcode.com/problems/generate-parentheses/
// Difficulty: Medium

// Approach:
// Use backtracking to generate all valid parentheses combinations.
// Maintain counts of open and close brackets.
// Add '(' if open < n.
// Add ')' if close < open.
// When the current string length reaches 2 * n, add it to the result.
// Backtrack after each recursive call.

// Time Complexity: O(4^n / √n)
// Space Complexity: O(n) (recursion stack)


class Solution {
    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max){
         if(cur.length() == max * 2){
            ans.add(cur.toString());
            return;
        }

        if(open < max){
            cur.append("(");
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }

        if(close < open){
            cur.append(")");
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
    
    public List<String> generateParenthesis(int n) {
       List<String> ans = new ArrayList<>();
       backtrack(ans, new StringBuilder(), 0, 0, n);
       return ans;
    }
}
