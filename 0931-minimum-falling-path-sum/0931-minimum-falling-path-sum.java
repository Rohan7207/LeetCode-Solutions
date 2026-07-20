class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;

        Integer[][] dp = new Integer[n][n];

        // for(int i = 0; i < n; i++) {
        //     Arrays.fill(dp[i], -1);
        // }

        int ans = Integer.MAX_VALUE;

        // Try every column in the first row as the starting point
        for(int col = 0; col < n; col++) {
            ans = Math.min(ans, helper(matrix, 0, col, dp));
        }

        return ans;
    }

    private int helper(int[][] matrix, int row, int col, Integer[][] dp) {
        int n = matrix.length;

        // Base Case 1:
        // If column goes out of bounds, return a very large value.
        if(col < 0 || col >= n) {
            return (int)1e9;
        }

        // Base Case 2:
        // If we reach the last row, return its value.
        if(row == n - 1) {
            return matrix[row][col];
        }

        // Memoization
        if(dp[row][col] != null) {
            return dp[row][col];
        }

        // Recursive calls
        int downLeft = helper(matrix, row + 1, col - 1, dp);
        int down = helper(matrix, row + 1, col, dp);
        int downRight = helper(matrix, row + 1, col + 1, dp);

        int minPath = Math.min(downLeft, Math.min(down, downRight));

        // Store and return answer
        dp[row][col] = matrix[row][col] + minPath;

        return dp[row][col];
    }
}