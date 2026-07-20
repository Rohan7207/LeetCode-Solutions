// Problem: Minimum Falling Path Sum
// Link: https://leetcode.com/problems/minimum-falling-path-sum/
// Difficulty: Medium

// Approach:
// Every cell in the first row can be the starting point of a falling path.
// For each starting column, recursively compute the minimum falling path sum
// from that cell to the last row, and return the minimum among all starting
// columns.
// Define:
// helper(row, col)
// It returns the minimum falling path sum starting from (row, col)
// and ending at the last row.
// From each cell, there are three possible moves:
// • Down Left   -> (row + 1, col - 1)
// • Down        -> (row + 1, col)
// • Down Right  -> (row + 1, col + 1)
// The recurrence is:
// helper(row, col)
// = matrix[row][col]
// + min(
//     helper(row+1, col-1),
//     helper(row+1, col),
//     helper(row+1, col+1)
//   )
// Base Cases:
// • If the column goes out of bounds, return a very large value so
//   that this path is never chosen.
// • If the last row is reached, return the value of the current cell.
// Memoization:
// Store the answer for every (row, col) in the dp table.
// If the same state is visited again, return the stored value instead
// of recomputing it.
// Finally, iterate through every column of the first row, call helper()
// for each starting position, and return the minimum answer.

// Time Complexity:
// O(n²)
//
// Space Complexity:
// O(n²) for memoization + O(n) recursion stack


class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;

        Integer[][] dp = new Integer[n][n];

        int ans = Integer.MAX_VALUE;

        // Try every column in the first row as the starting point
        for (int col = 0; col < n; col++) {
            ans = Math.min(ans, helper(matrix, 0, col, dp));
        }

        return ans;
    }

    private int helper(int[][] matrix, int row, int col, Integer[][] dp) {
        int n = matrix.length;

        // Base Case 1:
        // If column goes out of bounds, return a very large value.
        if (col < 0 || col >= n) {
            return (int) 1e9;
        }

        // Base Case 2:
        // If we reach the last row, return its value.
        if (row == n - 1) {
            return matrix[row][col];
        }

        // Memoization
        if (dp[row][col] != null) {
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
