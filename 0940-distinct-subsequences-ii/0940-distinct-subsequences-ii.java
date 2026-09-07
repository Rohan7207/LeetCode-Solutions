// Problem: Distinct Subsequences II
// Link: https://leetcode.com/problems/distinct-subsequences-ii/?envType=daily-question&envId=2026-09-07
// Difficulty: Hard

// Approach:
// Use Dynamic Programming + Memoization + Last Occurrence Tracking.
//
// 1. For every position i, store the previous occurrence of
//    s[i-1] in prev[i] using 1-based indexing.
//
// 2. Let solve(i) represent the number of distinct subsequences
//    (including the empty subsequence) that can be formed using
//    the first i characters.
//
// 3. For every new character, every existing subsequence gives
//    two choices:
//      - Do not include the current character.
//      - Include the current character.
//    Therefore, start with:
//      2 * solve(i - 1).
//
// 4. If the current character appeared before, some of the newly
//    generated subsequences are duplicates. These duplicates are
//    exactly the subsequences that could already be formed before
//    the previous occurrence of this character:
//      solve(prev[i] - 1)
//
// 5. Subtract those duplicates from the total.
//
// 6. solve(n) includes the empty subsequence, so subtract 1 before
//    returning the answer.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {

    int MOD = 1000000007;
    int[] prev; 
    int[] dp = new int[2001];

    public int distinctSubseqII(String s) {
        int n = s.length();
        int[] lastSeen = new int[26];
        prev = new int[n + 1];
        Arrays.fill(dp, -1);

        for (int i = 1; i <= n; i++) {
            int idx = s.charAt(i - 1) - 'a';

            prev[i] = lastSeen[idx];
            lastSeen[idx] = i;
        }

        return (solve(n) - 1 + MOD) % MOD;
    }

    private int solve(int n) {
        // Base Case
        if (n == 0) {
            return 1; 
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        int total = (2 * solve(n - 1)) % MOD;

        if (prev[n] != 0) {
            int duplicates = solve(prev[n] - 1);
            total = (total - duplicates + MOD) % MOD;
        }

        return dp[n] = total;
    }
}
