class Solution {
    public boolean stoneGame(int[] piles) {
        // Maximum score difference the current player can achieve from piles[left...right].
        int n = piles.length;
        int[][] dp = new int[n][n];

        // Fill the base case left == right
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        // Decide traversal order, Start with intervals of length 2.
        for (int len = 2; len <= n; len++) {
            // Find left and right
            for (int left = 0; left <= n - len; left++) {
                int right = left + len - 1;

                // Fill recurrence
                int takeLeft = piles[left] - dp[left + 1][right];
                int takeRight = piles[right] - dp[left][right - 1];

                dp[left][right] = Math.max(takeLeft, takeRight);
            }
        }

        return dp[0][n - 1] > 0;
    }
}