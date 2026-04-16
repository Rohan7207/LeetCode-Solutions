// Problem: Regular Expression Matching
// Link: https://leetcode.com/problems/regular-expression-matching/
// Difficulty: Hard

// Approach:
// Use Dynamic Programming where dp[i][j] represents whether
// s[0..i-1] matches p[0..j-1].

// 1. Initialize dp[0][0] = true (empty string matches empty pattern).

// 2. Handle patterns like a*, a*b*, etc., which can match empty string
//    by skipping the character and '*' (dp[0][j] = dp[0][j-2]).

// 3. Traverse the string and pattern:
//    - If characters match or pattern has '.', then:
//      dp[i][j] = dp[i-1][j-1]

//    - If pattern has '*', consider two cases:
//      a) Zero occurrence → dp[i][j] = dp[i][j-2]
//      b) One or more occurrence →
//         if previous pattern char matches current string char:
//         dp[i][j] |= dp[i-1][j]

// 4. Final answer will be dp[m][n].

// Key Concept:
// '*' allows skipping or repeating the previous character,
// making it necessary to consider multiple transitions.

// Time Complexity: O(m * n)

// Space Complexity: O(m * n)

class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        int mr = dp.length - 1;
        int mc = dp[0].length - 1;

        for(int i = 0; i <= mr; i++) {
            for(int j = 0; j <= mc; j++) {
                if(i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if(i == 0) {
                    char chp = p.charAt(j - 1);

                    if(chp == '*') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = false;
                    }
                } else if(j == 0) {
                    dp[i][j] = false;
                } else {
                    char chp = p.charAt(j - 1);
                    char chs = s.charAt(i - 1);

                    if(chp == '*') {
                        dp[i][j] = dp[i][j - 2];

                        char chprev = p.charAt(j - 2);
                        if(chprev == '.' || chprev == chs) {
                            dp[i][j] |= dp[i - 1][j];
                        }
                    } else if(chp == chs || chp == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }

        return dp[mr][mc];
    }
}

