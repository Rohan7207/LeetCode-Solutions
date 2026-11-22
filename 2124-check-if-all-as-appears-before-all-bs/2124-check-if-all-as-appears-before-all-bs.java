// Problem: Check if All A's Appears Before All B's
// Link: https://leetcode.com/problems/check-if-all-as-appears-before-all-bs/?envType=daily-question&envId=2026-11-21
// Difficulty: Easy

// Approach:
// 1. Initialize lastAPos = -1 and firstBPos = n.
// 2. Traverse the string:
//    a. Whenever 'a' is found, update lastAPos.
//    b. When 'b' is found for the first time, store its index in firstBPos.
// 3. After traversal, check whether lastAPos < firstBPos.
// 4. If true, every 'a' appears before every 'b'.
//    Otherwise, an 'a' occurs after a 'b'.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean checkString(String s) {
        int n = s.length();
        int lastAPos = -1;
        int firstBPos = n;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                lastAPos = i;
            } else if (ch == 'b' && firstBPos == n) {
                firstBPos = i;
            }
        }


        return lastAPos < firstBPos;
    }
}
