// Problem: Longest Increasing Path in a Matrix
// Link: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
// Difficulty: Hard

// Approach:
// Use DFS with memoization.
// dp[i][j] stores the longest increasing path
// starting from cell (i, j).
//
// Traverse every cell in the matrix.
// For each cell, run DFS only if its value
// is not already computed in dp.
//
// In DFS:
//     - Check all 4 directions:
//       up, down, left, right.
//     - Move only to a neighbour whose value
//       is greater than current cell.
//     - Take the maximum path among all valid moves.
//     - Add 1 for the current cell itself.
//     - Store the result in dp[i][j].
//
// Return the maximum value found among all cells.

// Time Complexity: O(m * n)
// Space Complexity: O(m * n)


class Solution {
    int[][] dp;
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(dp[i][j] == -1) {
                    ans = Math.max(ans, dfs(matrix, i, j));
                }
            }
        }

        return ans;
    }

    private int dfs(int[][] matrix, int i, int j) {
        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int num = matrix[i][j];

        int left = 0;
        int right = 0;
        int up = 0;
        int down = 0;

        if(i + 1 < m && matrix[i + 1][j] > num) {
            down = dfs(matrix, i + 1, j);
        }

        if(i - 1 >= 0 && matrix[i - 1][j] > num) {
            up = dfs(matrix, i - 1, j);
        }

        if(j + 1 < n && matrix[i][j + 1] > num) {
            right = dfs(matrix, i, j + 1);
        }

        if(j - 1 >= 0 && matrix[i][j - 1] > num) {
            left = dfs(matrix, i, j - 1);
        }

        dp[i][j] = 1 + Math.max(Math.max(up, down), Math.max(left, right));

        return dp[i][j];
    }
}
