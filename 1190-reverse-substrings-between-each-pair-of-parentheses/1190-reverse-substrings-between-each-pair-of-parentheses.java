// Problem: Reverse Substrings Between Each Pair of Parentheses
// Link: https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/?envType=daily-question&envId=2026-09-27
// Difficulty: Medium

// Approach:
//
// 1. First pass: find the matching parenthesis for every '(' and ')'.
//
//    Use a Stack<Integer>:
//      '(' → push its index
//      ')' → pop its matching '('
//
//    Store both directions:
//
//      bracketIdx[open]  = close
//      bracketIdx[close] = open
//
// 2. Second pass: traverse the string.
//
//    Normally:
//        flag = +1 → move left → right
//
//    When a parenthesis is encountered:
//        jump directly to its matching parenthesis
//        reverse the direction:
//
//        flag = -flag
//
// 3. Parentheses themselves are not added to the answer.
//
// 4. Because direction changes whenever we cross a pair of parentheses,
//    the characters inside that pair are effectively traversed backwards.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        Stack<Integer> openBracketIdx = new Stack<>();
        int[] bracketIdx = new int[n]; 

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                openBracketIdx.push(i);
            } else if (ch == ')') {
                int j = openBracketIdx.peek();
                openBracketIdx.pop();

                bracketIdx[i] = j;
                bracketIdx[j] = i;
            }
        }

        StringBuilder res = new StringBuilder();
        int flag = 1; 
        for (int i = 0; i < n; i += flag) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == ')') {
                i = bracketIdx[i];
                flag = -flag; 
            } else {
                res.append(ch);
            }
        }

        return res.toString();
    }
}
