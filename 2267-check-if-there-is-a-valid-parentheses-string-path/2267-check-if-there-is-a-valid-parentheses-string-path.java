class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][][] dp = new boolean[m][n][m + n]; // open count max value is m + n - 1

        if ((m + n - 1) % 2 == 1) { // bcz the total pairs must be even to be valid
            return false;
        }

        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        for (int i = m - 1; i >= 0; i--) { // bcz we need row + 1 values
            for (int j = n - 1; j >= 0; j--) { // same col + 1
                // i + j + 1 bcz at any cell we will traverse i rows i.e (i + 1) and j cols (j + 1) but since i is already covered we need -1 which gives (i + 1)(j + 1 - 1)  => i + j + 1 in worst case
                for (int open = 0; open <= i + j + 1; open++) {
                    if (i == m - 1 && j == n - 1) {
                        dp[i][j][open] = (open == 0);
                        continue;
                    }

                    dp[i][j][open] = false;

                    // down
                    //  we need open count value for this we know i and j values and we will ask down cell what is its value if '(' open++ or open--
                    if (i + 1 < m) {
                        int nextOpen = (grid[i + 1][j] == '(') ? open + 1 : open - 1;
                        if (nextOpen >= 0 && dp[i + 1][j][nextOpen]) { // if next cell is valid mark current as true
                            dp[i][j][open] = true;
                        }
                    }

                    // right
                    if (j + 1 < n) {
                        int nextOpen = grid[i][j + 1] == '(' ? open + 1 : open - 1;
                        if (nextOpen >= 0 && dp[i][j + 1][nextOpen]) { // if next cell is valid mark current as true
                            dp[i][j][open] = true;
                        }
                    }
                }
            }
        }

        // return solve(grid, 0, 0, 0);
        // 1 bcz we checked starting condition grid[0][0]=')' we returned false so then if it is not met then definetely open count would be 1
        return dp[0][0][1]; // O(m * n (m + n))
    }
}

/*
    This is recursive with memo above is bottom-up
    class Solution {
    int m, n;
    Boolean[][][] dp;  // Time = states O(m * n (m + n))

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
*/