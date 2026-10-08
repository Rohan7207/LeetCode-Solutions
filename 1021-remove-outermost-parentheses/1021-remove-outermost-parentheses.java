// Problem: Remove Outermost Parentheses
// Link: https://leetcode.com/problems/remove-outermost-parentheses/?envType=daily-question&envId=2026-10-08
// Difficulty: Easy

// Approach:
// 1. Maintain `count` as the current parentheses nesting depth.
// 2. For '(':
//      - Increase count first.
//      - If count > 1, it is not an outermost '(' → add it.
//      - If count == 1, it is the outermost '(' → skip it.
// 3. For ')':
//      - If count > 1, it is not an outermost ')' → add it.
//      - If count == 1, it is the outermost ')' → skip it.
//      - Decrease count after processing.
// 4. This removes the first and last parenthesis of every primitive group
//    without separately finding the start/end of each group.
// 5. Return the constructed string.

// Time Complexity: O(n)
// Space Complexity: O(n) for the output StringBuilder.


class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                count++;
                if (count > 1) {
                    sb.append(ch);
                }
            } else {
                if (count > 1) {
                    sb.append(ch);
                }

                count--;
            }
        }
        
        return sb.toString();
    }
}
