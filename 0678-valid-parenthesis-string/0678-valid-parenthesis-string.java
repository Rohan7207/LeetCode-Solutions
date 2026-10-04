// Problem: Valid Parenthesis String
// Link: https://leetcode.com/problems/valid-parenthesis-string/?envType=daily-question&envId=2026-10-04
// Difficulty: Medium

// Approach:
// 1. Maintain a range of possible unmatched '(':
//    minOpen = minimum possible unmatched '('
//    maxOpen = maximum possible unmatched '('
//
// 2. For '(':
//    Both minimum and maximum increase.
//
// 3. For ')':
//    Both decrease because it must close an opening parenthesis.
//
// 4. For '*':
//    It can be ')', '(', or empty.
//    So minOpen-- and maxOpen++.
//
// 5. If maxOpen < 0:
//    Even the most optimistic interpretation cannot make the string valid.
//    Return false.
//
// 6. minOpen cannot go below 0 because we cannot have negative
//    unmatched opening parentheses.
//    So: minOpen = Math.max(minOpen, 0)
//
// 7. After processing the whole string:
//    If minOpen == 0, there exists some interpretation of '*' that
//    balances all parentheses.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0; 
        int maxOpen = 0; 

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else {
                minOpen--;
                maxOpen++; '
            }

            if (maxOpen < 0) {
                return false;
            }

            minOpen = Math.max(minOpen, 0);
        }

        return minOpen == 0;
    }
}   
