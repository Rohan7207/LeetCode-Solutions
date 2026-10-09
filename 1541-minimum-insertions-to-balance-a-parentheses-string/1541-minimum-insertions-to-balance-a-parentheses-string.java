// Problem: Minimum Insertions to Balance a Parenthses String
// Link: https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/?envType=daily-question&envId=2026-10-09
// Difficulty: Medium

// Approach:
// 1. `count` tracks unmatched '(' characters.
// 2. `res` tracks the number of insertions required.
// 3. For '(' → increment count.
// 4. For ')' →
//      - If count > 0, match an opening parenthesis and decrement count.
//      - Otherwise, insert '(' and increment res.
// 5. Check whether the current ')' has a consecutive ')' after it.
//      - If yes, consume both.
//      - Otherwise, insert the missing ')' and increment res.
// 6. After traversal, every unmatched '(' requires two ')'.
// 7. Return res + count * 2.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int minInsertions(String s) {
        int res = 0;
        int count = 0;
        int n = s.length();
        int i = 0;

        while (i < n) {
            if (s.charAt(i) == '(') {
                count++;
                i++;
            } else {
                if (count > 0) { 
                    count--;
                } else {
                    res++;
                }

                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i += 2; 
                } else {
                    res++; 
                    i++;
                }
            }
        }

        return res + count * 2;
    }
}
