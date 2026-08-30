// Problem: Minimum Remove to Make Valid Parenthesis
// Link: https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
// Difficulty: Medium

// Approach:
// Use Stack + Set of Invalid Indices.
//
// 1. Traverse the string character by character.
//
// 2. When we encounter '(':
//    Store its index in the stack because it may need a matching ')'
//
// 3. When we encounter ')':
//
//      - If the stack is not empty, pop the matching '('.
//      - If the stack is empty, this ')' has no matching '(',
//        so mark its index for removal.
//
// 4. After traversal, any '(' indices remaining in the stack
//    do not have matching ')' characters.
//    Add all those indices to the removal set.
//
// 5. Traverse the string again and build the result by skipping
//    every index present in `removeIndices`.
//
// 6. The remaining string contains only valid parentheses.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String minRemoveToMakeValid(String s) {
        Set<Integer> removeIndices = new HashSet<>();
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(i);
            } else if (c == ')') {
                if (st.isEmpty()) {
                    removeIndices.add(i);
                } else {
                    st.pop();
                }
            }
        }

        //Add remaining parenthesis present in stack
        while (!st.isEmpty()) {
            removeIndices.add(st.pop());
        }

        //Add characters which are not present in set to result string
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!removeIndices.contains(i)) {
                res.append(s.charAt(i));
            }
        }

        return res.toString();
    }
}
