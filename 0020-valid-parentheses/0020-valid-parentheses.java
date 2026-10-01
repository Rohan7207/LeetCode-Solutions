// Problem: Valid Parentheses
// Link: https://leetcode.com/problems/valid-parentheses/?envType=daily-question&envId=2026-10-01
// Difficulty: Easy

// Approach:
// 1. Use a Stack to keep track of opening brackets.
//
// 2. Traverse the string from left to right.
//    - If the character is an opening bracket, push it onto the stack.
//    - If it is a closing bracket, it must match the most recent
//      opening bracket.
//
// 3. For every closing bracket:
//    - If the stack is empty, there is no opening bracket to match it.
//    - Pop the top opening bracket.
//    - Check whether it matches the current closing bracket.
//
// 4. If any closing bracket doesn't match, return false.
//
// 5. After processing the entire string, the stack must be empty.
//    Otherwise, some opening brackets were never closed.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false; 
                }

                char top = stack.pop();
                if ((c == ')' && top != '(')
                        || (c == ']' && top != '[')
                        || (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
