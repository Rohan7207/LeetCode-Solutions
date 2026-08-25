// Problem: Split a String in Balanced Strings
// Link: https://leetcode.com/problems/split-a-string-in-balanced-strings/
// Difficulty: Easy

// Approach:
// Use a Greedy approach with a balance counter.
//
// 1. Treat every 'L' as +1 and every 'R' as -1.
//
// 2. Maintain `count` as the balance between L and R:
//
//      L → count++
//      R → count--
//
// 3. Whenever `count == 0`, the number of L's and R's seen
//    in the current segment is equal.
//
// 4. Therefore, we have found one balanced substring.
//    Increment `ans`.
//
// 5. Continue scanning the string. Every time the balance
//    returns to zero, we can safely split another balanced
//    substring.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int balancedStringSplit(String s) {
        int count = 0;
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                ans++;
            }
        }

        return ans;
    }
}
