class Solution {

    int[][] memo;
    int[] prefixSum;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        memo = new int[n][n];
        prefixSum = new int[n + 1];

        // Compute prefix sums for O(1) subarray sum queries
        // 0, 6, 8, 11, 15, 20, 25
        for(int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + stoneValue[i];
        }

        return solve(0, n - 1);
    }   

    private int solve(int left, int right) {
        // Base case: only one stone left, game ends (no further score)
        if(left == right) {
            return 0;
        }

        if(memo[left][right] != 0) {
            return memo[left][right];
        }

        int maxScore = 0;

        for(int i = left; i < right; i++) {
            int leftSum = prefixSum[i + 1] - prefixSum[left];
            int rightSum = prefixSum[right + 1] - prefixSum[i + 1];

            if(leftSum < rightSum) {
                maxScore = Math.max(maxScore, leftSum + solve(left, i));
            } else if(leftSum > rightSum) {
                maxScore = Math.max(maxScore, rightSum + solve(i + 1, right));
            } else {
                maxScore = Math.max(maxScore, leftSum + Math.max(solve(left, i), solve(i + 1, right)));
            }
        }

        memo[left][right] = maxScore;
        return maxScore;
    }
}

/*
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
*/