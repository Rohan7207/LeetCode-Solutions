// Problem: Number of Sets of K Non-Overlapping Line Segments
// Link: https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments/?envType=daily-question&envId=2026-09-16
// Difficulty: Medium

// Approach:
// Use 2D DP + suffix sum optimization.
//
// 1. Initialize dp[0][i] = 1 because choosing 0 segments gives one way.
// 2. Let dp[K][i] represent the number of ways to draw K segments
//    using points from index i onward.
// 3. Build `prevRowSum` to store suffix sums of dp[K - 1].
// 4. For each starting point i:
//    - Take: prevRowSum[i + 1] (choose the next endpoint).
//    - Skip: dp[K][i + 1] (ignore the current point).
// 5. Combine both choices and apply modulo.

// Time Complexity: O(n × k)
// Space Complexity: O(n × k)


class Solution {

    private int MOD = 1000000007;

    public int numberOfSets(int n, int k) {
        long[][] dp = new long[1001][1001];

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int K = 1; K <= k; K++) {              
            long[] prevRowSum = new long[n + 1];

            for (int x = n - 1; x >= 0; x--) {
                prevRowSum[x] = (prevRowSum[x + 1] + dp[K - 1][x]) % MOD;
            }

            for (int i = n - 1; i >= 0; i--) { 
                long take = prevRowSum[i + 1];
                long skip = dp[K][i + 1];

                dp[K][i] = (take + skip) % MOD;
            }
        }

        return (int) (dp[k][0]) % MOD;
    }
}
