// Problem: Unique Paths II
// Link: https://leetcode.com/problems/unique-paths-ii/
// Difficulty: Medium

// Approach:
// Use dynamic programming with a 1D array to optimize space.
// Create a dp array where dp[c] stores the number
//     of ways to reach the current cell in the current row.
// Initialize dp[0] = 1 as the starting position.
// Traverse the grid row by row:
//     - If the current cell is an obstacle,
//       set dp[c] = 0 because it cannot be reached.
//     - Otherwise, add paths from the left cell:
//           dp[c] += dp[c - 1]
// At the end, the last element of dp contains total unique paths.

// Time Complexity: O(m * n)
// Space Complexity: O(n)


class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        if (obstacleGrid == null || obstacleGrid[0][0] == 1) {
            return 0;
        }

        int[] dp = new int[cols];
        dp[0] = 1; 

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (obstacleGrid[r][c] == 1) {
                    dp[c] = 0; 
                } else {
                    if (c > 0) { 
                        dp[c] += dp[c - 1];
                    }
                }
            }
        }

        return dp[cols - 1];
    }
}
