class Solution {

    int[][] dp;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        dp = new int[n][n];

        return dfs(stoneValue, 0, n - 1);
    }

    private int dfs(int[] stoneValue, int left, int right) {
        if (left == right) {
            return 0;
        }

        if (dp[left][right] != 0) {
            return dp[left][right];
        }

        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += stoneValue[i];
        }

        int suml = 0;
        for (int i = left; i < right; i++) {
            suml += stoneValue[i];
            int sumr = sum - suml;

            if (suml < sumr) {
                dp[left][right] = Math.max(dp[left][right], dfs(stoneValue, left, i) + suml);
            } else if (suml > sumr) {
                dp[left][right] = Math.max(dp[left][right], dfs(stoneValue, i + 1, right) + sumr);
            } else {
                dp[left][right] = Math.max(dp[left][right],
                        Math.max(dfs(stoneValue, left, i), dfs(stoneValue, i + 1, right)) + suml);
            }
        }

        return dp[left][right];
    }
}