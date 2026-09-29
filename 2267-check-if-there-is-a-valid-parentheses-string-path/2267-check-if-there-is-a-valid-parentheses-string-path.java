// Problem: Check if There Is a Valid Parentheses String Path
// Link: https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path/?envType=daily-question&envId=2026-09-29
// Difficulty: Hard

// Approach:
// 1. A valid path must contain an equal number of '(' and ')'.
//    Therefore, the total number of cells (m + n - 1) must be even.
//
// 2. Start from (0, 0) with open = 0.
//    `open` represents the number of unmatched '(' seen so far.
//
// 3. For every cell:
//    - '(' → open++
//    - ')' → open--
//
// 4. If open becomes negative, the path is invalid because we have
//    encountered ')' without a matching '('.
//
// 5. From each cell, we can move only:
//    - down
//    - right
//
// 6. The same state can be reached through multiple paths.
//    So memoize:
//
//       dp[row][col][open]
//
//    This means: whether a valid path exists from this cell with
//    the current number of unmatched '('.
//
// 7. At the bottom-right cell, the path is valid only when open == 0.
//
// 8. Use Boolean instead of boolean so that:
//    null  → state not calculated
//    true  → valid
//    false → invalid

// Time Complexity: O(m * n * (m + n))
// Space Complexity: O(m * n * (m + n))


class Solution {

    int m, n;
    Boolean[][][] dp; 

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        dp = new Boolean[m][n][m + n]; 

        if ((m + n - 1) % 2 == 1) {
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
