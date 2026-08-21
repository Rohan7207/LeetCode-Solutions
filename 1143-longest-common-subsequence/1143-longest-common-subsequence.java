// Problem: Longest Common Subsequence
// Link: https://leetcode.com/problems/longest-common-subsequence/
// Difficulty: Medium

// Approach:
// Use Dynamic Programming to find the Longest Common Subsequence (LCS).
//
// 1. Create a 2D DP table where:
//      dp[i][j] = LCS length between
//                 first i characters of text1 and
//                 first j characters of text2.
//
// 2. Compare the current characters:
//      text1[i - 1] and text2[j - 1]
//
// 3. If they are equal:
//      The current character can be part of the LCS.
//      dp[i][j] = dp[i - 1][j - 1] + 1
//
// 4. If they are different:
//      We have two choices:
//      - Ignore the current character of text1
//      - Ignore the current character of text2
//
//      Take the better one:
//      dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
//
// 5. dp[m][n] contains the LCS length.

// Time Complexity: O(m × n)
// Space Complexity: O(m × n)


class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}
