class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];

        // We need j = i + 1,
        for(int i = n - 1; i >= 0; i--) {
            int cost = Integer.MAX_VALUE;

            int j = i + 1;
            if (j <= n) { // Jump of i + 1
                cost = costs[j - 1] + (j - i) * (j - i) + dp[j];
            }

            j = i + 2;
            if (j <= n) { // Jump of i + 2
                cost = Math.min(cost, costs[j - 1] + (j - i) * (j - i) + dp[j]);
            }

            j = i + 3;
            if (j <= n) { // Jump of i + 3
                cost = Math.min(cost, costs[j - 1] + (j - i) * (j - i) + dp[j]);
            }

            dp[i] = cost;
        }

        return dp[0];
    }
}

/*
    class Solution {

    private int[] memo;

    public int climbStairs(int n, int[] costs) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);

        return solve(0, n, costs);
    }

    private int solve(int i, int n, int[] costs) {
        // Base case:
        if (i == n) {
            return 0;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        int cost = Integer.MAX_VALUE;

        int j = i + 1;
        if (j <= n) { // Jump of i + 1
            cost = Math.min(cost, costs[j - 1] + (j - i) * (j - i) + solve(j, n, costs));
        }

        j = i + 2;
        if (j <= n) { // Jump of i + 2
            cost = Math.min(cost, costs[j - 1] + (j - i) * (j - i) + solve(j, n, costs));
        }

        j = i + 3;
        if (j <= n) { // Jump of i + 3
            cost = Math.min(cost, costs[j - 1] + (j - i) * (j - i) + solve(j, n, costs));
        }

        return memo[i] = cost;
    }
}
*/