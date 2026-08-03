class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1]; // Bcz we can choose 3 stones so when i is near the end, these indices should safely exist and represent a score difference of 0.

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;

            int take = 0;

            // Using a running sum avoids recomputing sums repeatedly.
            for (int k = 0; k < 3 && k + i < n; k++) {
                take += stoneValue[i + k];

                dp[i] = Math.max(dp[i], take - dp[i + k + 1]);
            }
        }

        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        }

        return "Tie";
    }
}