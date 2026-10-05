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

// Time Complexity: O(n)
// Space Complexity: O(1)

// Treat every primitive "()" as contributing 2^(remaining depth) and sum all such contributions.

/*
    🔑 Key Observation

Every balanced parentheses expression is composed of primitive "()" pairs. Each enclosing pair doubles the contribution of that primitive pair.

✨ Magic Line / Important Line
score += 1 << depth;

This adds the contribution of the current primitive "()", where 1 << depth is equal to 2^depth.

💡 How We Thought to Derive the Solution
The base score comes only from "()".
Wrapping an expression doubles its score.
Therefore, instead of evaluating whole expressions, count the contribution of each primitive "()".
The number of enclosing parentheses determines how many times its score is doubled.

✅ Why It Works
Every primitive "()" starts with a score of 1.
If it is enclosed by depth pairs, its final contribution becomes 2^depth.
Summing the contributions of all primitive pairs gives exactly the score defined by the problem rules.

🧩 Pattern Recognition
Parentheses Processing
Depth Tracking
Bit Manipulation
Mathematical Observation

⭐ Interview Importance

⭐⭐⭐⭐☆

A great interview problem that has both a stack-based solution and a more elegant O(1) space solution using nesting depth.

📚 Similar Problems
LeetCode 20 – Valid Parentheses
LeetCode 32 – Longest Valid Parentheses
LeetCode 394 – Decode String
LeetCode 946 – Validate Stack Sequences
LeetCode 1614 – Maximum Nesting Depth of the Parentheses
*/

/*
    Stack<Integer> st = new Stack<>();
        int score = 0;

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                // Save current outer score and reset for the inner context
                st.push(score);
                score = 0;
            } else {
                // If it's a direct pair "()", add 1 point
                if(s.charAt(i - 1) == '(') {
                    score = st.pop() + 1;
                } else {
                    // If it's a closed outer structure "(A)", double inner score and add to outer score
                    score = st.pop() + 2 * score;
                }
            }
        }

        return score;
*/