// Problem: Basic Calculator
// Link: https://leetcode.com/problems/basic-calculator/
// Difficulty: Hard

// Approach:
// Traverse the string character by character.
// Maintain three variables:
//     - curr stores the current number being formed.
//     - res stores the evaluated result so far.
//     - sign stores whether current number is positive or negative.
// If character is digit:
//     - Build the number using curr = curr * 10 + digit.
// If character is '+' or '-':
//     - Add previous number to result using sign.
//     - Update sign for next number.
//     - Reset curr to 0.
// If character is '(':
//     - Push current result and sign into stack.
//     - Reset result and sign for evaluating
//       the expression inside parentheses.
// If character is ')':
//     - Add current number to result.
//     - Apply sign before parenthesis.
//     - Add previous result before parenthesis.
// After traversal, add the last pending number.
// Return final result.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int calculate(String s) {
        int res = 0;
        int sign = 1;
        int curr = 0;

        Stack<Integer> stack = new Stack<>();

        for(char c : s.toCharArray()) {
            if(Character.isDigit(c)) {
                curr = curr * 10 + (c - '0');
            } else if (c == '+') {
                res += sign * curr;
                sign = 1;
                curr = 0;
            } else if(c == '-') {
                res += sign * curr;
                sign = -1;
                curr = 0;
            } else if(c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
                curr = 0;
            } else if(c == ')') {
                res += sign * curr;
                curr = 0;
                res *= stack.pop();
                res += stack.pop();
            }
        }

        res += sign * curr;

        return res;
    }
}
