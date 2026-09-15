// Problem: Maximum Number of Non-overlapping Palindrome Substrings
// Link: https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings/?envType=daily-question&envId=2026-09-15
// Difficulty: Hard

// Approach:
// Use 1D prefix DP + palindrome preprocessing.
//
// 1. Precompute `pal[i][j]` to know whether s[i...j] is a palindrome.
// 2. Let `dp[len]` = maximum number of valid non-overlapping palindromes
//    that can be chosen from the first `len` characters.
// 3. For every `len`, first consider skipping the last character:
//       dp[len] = dp[len - 1]
// 4. Let `j = len - 1`. Try every starting index `i` such that
//    s[i...j] has length at least k.
// 5. If s[i...j] is a palindrome, take it and combine it with the
//    best answer before it:
//       1 + dp[i]
// 6. Take the maximum between skipping the character and taking a palindrome.

// Time Complexity: O(n²)
// Space Complexity: O(n²)


class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int[] dp = new int[n + 1];

        // Precompute Palindrome
        boolean[][] pal = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) &&
                        (j - i < 2 || pal[i + 1][j - 1])) {
                    pal[i][j] = true;
                }
            }
        }

        for(int len = 0; len < k; len++) {
            dp[len] = 0;
        }

        for(int len = k; len <= n; len++) {
            // int res = solve(s, k, len - 1)
            int res = dp[len - 1];
            int j = len - 1;

            for(int i = 0; j - i + 1 >= k; i++) {
                if(pal[i][j]) {
                    res = Math.max(res, 1 + dp[i]);
                }
            }

            dp[len] = res;
        }

        return dp[n];
    }
}
