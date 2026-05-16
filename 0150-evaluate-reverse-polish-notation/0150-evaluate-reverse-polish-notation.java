// Problem: Evaluate Reverse Polish Notation
// Link: https://leetcode.com/problems/evaluate-reverse-polish-notation/
// Difficulty: Medium

// Approach:
// Use a stack to evaluate the postfix expression.
// Traverse each token in the array:
//     - If token is a number,
//       push it into stack.
//     - If token is an operator,
//       pop top two elements from stack,
//       perform the operation,
//       and push the result back.
// Continue until all tokens are processed.
// The final element in stack is the answer.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                int b = s.pop();
                int a = s.pop();
                int res = operation(token, a, b);
                s.push(res);
            } else {
                s.push(Integer.parseInt(token));
            }
        }
        
        return s.pop();
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private int operation(String token, int a, int b) {
        switch (token) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
        }

        return 0;
    }
}
