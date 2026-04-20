// Problem: Valid Parenetheses
// Link: https://leetcode.com/problems/valid-parentheses/
// Difficulty: Easy

// Approach:
// 1. Use a stack to keep track of opening brackets.
//
// 2. Traverse each character in the string:
//    - If it is an opening bracket '(', '{', '[' → push it into the stack.
//
//    - If it is a closing bracket:
//        a) If stack is empty → return false (no matching opening).
//        b) Pop the top element from the stack.
//        c) Check if it matches the current closing bracket.
//           If not → return false.
//
// 3. After traversal, check if stack is empty:
//    - If empty → all brackets matched → return true.
//    - Else → return false.

// Time Complexity: O(n)
// Space Complexity: O(n)

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if(stack.isEmpty()) return false;
                char top = stack.pop();
                if((c == ')' && top != '(') 
                    || (c == ']' && top != '[')
                    || (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
