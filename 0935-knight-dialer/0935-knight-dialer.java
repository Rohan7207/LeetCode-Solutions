class Solution {

    private static final int MOD = 1_000_000_007;

    // Knight moves from each digit
    int[][] moves = {
            { 4, 6 }, // 0
            { 6, 8 }, // 1
            { 7, 9 }, // 2
            { 4, 8 }, // 3
            { 0, 3, 9 }, // 4
            {}, // 5
            { 0, 1, 7 }, // 6
            { 2, 6 }, // 7
            { 1, 3 }, // 8
            { 2, 4 } // 9
    };

    public int knightDialer(int n) {
        Integer[][] dp = new Integer[10][n + 1];

        long ans = 0;

        // Try every digit as the starting digit
        for (int digit = 0; digit <= 9; digit++) {
            ans = (ans + helper(digit, n, dp)) % MOD;
        }

        return (int) ans;
    }

    private int helper(int digit, int len, Integer[][] dp) {
        // Base Case
        if (len == 1) {
            return 1;
        }

        // Memoization
        if (dp[digit][len] != null) {
            return dp[digit][len];
        }

        long ways = 0;

        // Try every valid knight move
        for (int move : moves[digit]) {
            ways = (ways + helper(move, len - 1, dp)) % MOD;
        }

        dp[digit][len] = (int) (ways % MOD);

        return dp[digit][len];
    }
}