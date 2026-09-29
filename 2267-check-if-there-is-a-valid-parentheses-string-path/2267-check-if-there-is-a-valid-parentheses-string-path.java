class Solution {
    int m, n;
    Boolean[][][] dp;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        dp = new Boolean[m][n][m + n]; // open count max value is m + n - 1

        if ((m + n - 1) % 2 == 1) { // bcz the total pairs must be even to be valid
            return false;
        }

        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        return solve(grid, 0, 0, 0);
    }

    private boolean solve(char[][] grid, int row, int col, int open) {
        if (row < 0 || row >= m || col < 0 || col >= n) {
            return false;
        }

        if (grid[row][col] == '(') {
            open++;
        } else {
            open--;
        }

        if (open < 0) {
            return false;
        }

        if (dp[row][col][open] != null) {
            return dp[row][col][open];
        }

        if (row == m - 1 && col == n - 1) {
            return dp[row][col][open] = (open == 0);
        }

        // down
        if (solve(grid, row + 1, col, open)) {
            return dp[row][col][open] = true;
        }

        // right
        if (solve(grid, row, col + 1, open)) {
            return dp[row][col][open] = true;
        }

        return dp[row][col][open] = false;
    }
}