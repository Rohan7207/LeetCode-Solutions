class Solution {
    public int numTrees(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        // dp[2] = 2
        // dp[3] = 5 = 2 + 2 + 1
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}

/*
    O(n^2) and O(n)
    For no.of right nodes is = all nodes - current root
    For no.of left nodes is = current root - 1

    1 1 2 5 1

        int[] uniqTree = new int[n + 1];
        for(int i = 0; i <= n; i++){
            uniqTree[i] = 1;
        }

        for(int nodes = 2; nodes <= n; nodes++){
            int total = 0;
            for(int root = 1; root <= nodes; root++){
                total += uniqTree[root - 1] * uniqTree[nodes - root];
            }
            uniqTree[nodes] = total;
        }

        return uniqTree[n];
*/