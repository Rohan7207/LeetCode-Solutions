// Problem: Maximum Nesting Depth of the Parentheses
// Link: https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/?envType=daily-question&envId=2026-09-27
// Difficulty: Easy

// Approach:
//
// 1. Traverse the string from left to right.
//
// 2. Maintain `open` = current number of unmatched opening brackets.
//
// 3. When we see '(':
//      → increase open
//      → this may create a new maximum depth.
//
// 4. When we see ')':
//      → decrease open because one nested level is closed.
//
// 5. Keep the maximum value reached by `open`.
//
// 6. Return that maximum.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int maxDepth(String s) {
        int open = 0;
        int maxOpen = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                open++;

                if (maxOpen < open) {
                    maxOpen = open;
                }
            } else if (ch == ')') {
                open--;
            }
        }

        return maxOpen;
    }
}
