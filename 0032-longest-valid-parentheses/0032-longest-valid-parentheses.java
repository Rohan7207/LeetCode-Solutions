// Problem: Longest Valid Parentheses
// Link: https://leetcode.com/problems/longest-valid-parentheses/?envType=daily-question&envId=2026-10-03
// Difficulty: Hard

// Approach:
// 1. Use a stack to store indices of unmatched '('.
//
// 2. Push -1 initially.
//    This acts as a base index before the current valid substring.
//
// 3. When we see '(':
//    Push its index because it may need to be matched later.
//
// 4. When we see ')':
//    Pop the matching '(' from the stack.
//
//    - If the stack becomes empty, there is no valid '(' before this ')',
//      so the current index becomes the new base.
//
//    - Otherwise, the stack's top is the index just before the current
//      valid substring. Therefore:
//
//          length = i - st.peek()
//
// 5. Keep updating the maximum length.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int longestValidParentheses(String s) {
        int maxlength = 0;
        Stack<Integer> st = new Stack<>();
        st.push(-1); 

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
