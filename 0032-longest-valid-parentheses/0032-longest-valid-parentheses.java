// Problem: Longest Valid Parentheses
// Link: https://leetcode.com/problems/longest-valid-parentheses/
// Difficulty: Hard

// Approach:
// Use a stack to track indices of parentheses.
// Push -1 initially to act as the base for valid substring calculation.
// Traverse the string:
//     - If the current character is '(', push its index onto the stack.
//     - Otherwise, pop the top element from the stack.
//         - If the stack becomes empty, push the current index as the new base.
//         - Otherwise, calculate the length of the current valid substring
//           using currentIndex - stack.peek() and update the maximum length.
// Return the maximum valid parentheses length.

// Time Complexity: O(n)
// Space Complexity: O(n)

class Solution {
    public int longestValidParentheses(String s) {
        int maxlength = 0;
        Stack<Integer> st = new Stack<>();
        st.push(-1); //Initial value

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else {
                st.pop();

                if (st.isEmpty()) {
                    //Push the current index as base for next substring
                    st.push(i);
                } else {
                    maxlength = Math.max(maxlength, i - st.peek());
                }
            }
        }
        
        return maxlength;
    }
}
