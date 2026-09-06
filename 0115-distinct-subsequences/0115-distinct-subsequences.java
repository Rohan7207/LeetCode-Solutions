// Problem: Distinct Subsequences
// Link: https://leetcode.com/problems/distinct-subsequences/?envType=daily-question&envId=2026-09-06
// Difficulty: Hard

// Approach:
// Use Dynamic Programming with Space Optimization.
//
// 1. Define the original DP state:
//      dp[i][j] = number of ways to form t[0...j-1]
//                 using s[0...i-1].
//
// 2. If s[i-1] != t[j-1]:
//      We cannot use s[i-1], so carry the previous result:
//      dp[i][j] = dp[i-1][j].
//
// 3. If s[i-1] == t[j-1], we have two choices:
//      - Take s[i-1] → dp[i-1][j-1]
//      - Skip s[i-1] → dp[i-1][j]
//      Therefore:
//      dp[i][j] = dp[i-1][j-1] + dp[i-1][j].
//
// 4. The transition only depends on the previous row,
//    so instead of a 2D table, maintain:
//      prev → previous row
//      curr → current row.
//
// 5. Base case:
//      dp[i][0] = 1
//    because there is exactly one way to form an empty string:
//    choose nothing.
//
// 6. After processing all characters of s, prev[n] contains
//    the number of distinct subsequences equal to t.

// Time Complexity: O(m × n)
// Space Complexity: O(n)


class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[] prev = new int[n + 1];
        prev[0] = 1;

        for(int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            curr[0] = 1;

            for(int j = 1; j <= n; j++) {
                if(s.charAt(i - 1) == t.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + prev[j];
                } else {
                    curr[j] = prev[j];
                }
            }

            prev = curr;
        }

        return prev[n];
    }
}
