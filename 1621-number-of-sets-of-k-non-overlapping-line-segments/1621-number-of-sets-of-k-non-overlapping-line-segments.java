class Solution {

    private int MOD = 1000000007;

    public int numberOfSets(int n, int k) {
        long[][] dp = new long[1001][1001];

        // Base case: k == 0,  then dp[0][n] = 1, i >= n, dp value is  0
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int K = 1; K <= k; K++) { // We need previous values, and K = 1 bcz K = 0 is base case
            // Stores the suffix sum of prev k row from dp[k - 1][i + 1....n - 1]                
            long[] prevRowSum = new long[n + 1];

            for (int x = n - 1; x >= 0; x--) {
                prevRowSum[x] = (prevRowSum[x + 1] + dp[K - 1][x]) % MOD;
            }

            for (int i = n - 1; i >= 0; i--) { // We need future values to skip
                long take = prevRowSum[i + 1];
                long skip = dp[K][i + 1];

                dp[K][i] = (take + skip) % MOD;
            }
        }

        // return (int) (solve(n, k, 0) % MOD);
        return (int) (dp[k][0]) % MOD;
    }
}

/*
    // Recursion and got TLE lets see bottom-up version passes or not
    // O(n * k) states and there inner loop so O(n ^ 2 * k)
    private long[][] dp = new long[1001][1001];  
    private int MOD = 1000000007;

    public int numberOfSets(int n, int k) {
        return (int) (solve(n, k, 0) % MOD);
    }

    private long solve(int n, int k, int i) {
        // Base Case
        if(k == 0) {
            return 1;    // Found one way which can draw k non-overlapping line segment
        }

        if(i >= n) {
            return 0;
        }

        if(dp[k][i] != 0) {
            return dp[k][i];
        }

        // Since we have option to stop anywhere so we loop till n and try every way
        long take = 0;   // Start segment from ith point
        for(int j = i + 1; j < n; j++) {
            take = (take + solve(n, k - 1, j)) % MOD;   // j + 1 not bcz overlap is allowed so j
        }

        long skip = (solve(n, k, i + 1)) % MOD;   // Call for next point

        return dp[k][i] = (take + skip) % MOD;
    }
*/

/*
    Bottom-up of above recursion which got TLE and O(n ^ 2 * k)
    private int MOD = 1000000007;

    public int numberOfSets(int n, int k) {
        long[][] dp = new long[1001][1001];  

        // Base case: k == 0,  then dp[0][n] = 1, i >= n, dp value is  0
        for(int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for(int K = 1; K <= k; K++) {   // We need previous values, and K = 1 bcz K = 0 is base case
            for(int i = n - 1; i >= 0; i--) {   // We need future values to skip
                long take = 0;
                for(int j = i + 1; j < n; j++) {
                    take = (take + dp[K - 1][j]) % MOD;
                }

                long skip = dp[K][i + 1];

                dp[K][i] = (take + skip) % MOD;
            }
        }

        // return (int) (solve(n, k, 0) % MOD);
        return (int) (dp[k][0]) % MOD;
    }

    // In this we need to compulsorily run k and i loop but j loop makes it run O(n) time so optimization
        is involved there 

        for(int j = i + 1; j < n; j++) {
            take = (take + dp[K - 1][j]) % MOD;
        }

    - What do we need at each loop of k we need value of dp[k - 1][i + 1...n - 1] right
    - We can calculate dp[k - 1][i + 1...n - 1] can be done using suffix sum
    - If there was k * n matrix and we are now at k level we need sum values at k - 1 previous values
        so we can store the sum of previous row values such that,
         prevRow[i + 1...n - 1] => sum from dp[k - 1][i + 1] to dp[k - 1][n - 1];

        prevRow = suffix sum
        instead of j loop 
        for(int k = 1; k <= K; k++) {
            long[] prevRowSum = new long[n + 1];
            for(int x = n - 1; x >= 0; x--) {  // O(1)
                prevRowSum[x] = (prevRowSum[x + 1] + dp[k - 1][x]) % MOD;
            }

            for(int i = n - 1; i >= 0; i--) {
                int take =  prevRowSum[i +1];  // O(1)
                int skip = dp[k][i + 1];

                dp[k][i] = (take + skip) % MOD;
            }
        }

        return dp[k][0];   // O(n * k)
*/

/*  
    We have two option:
    - Start from point i
    - Skip and move to next point
    solve(n, k, 0);

    int solve(n, k, i) {
        if(k == 0) {
            return 1;   // Found one way to draw k non-overlapping line segments.
        }

        if(i >= n)  {
            return 0;    // Out of bounds
        }

        skip = solve(n, k, i + 1);
        // Start a segment from i
        for(int i = j + 1; j <= n - 1; j++) {  // Since we have option to stop anywhere so we loop till n
            take += solve(n, k - 1, j) % M;     // j bcz overlapping is allowed
        }

        return skip + take;
    }

    Memoization: dp[1001][1001]
*/