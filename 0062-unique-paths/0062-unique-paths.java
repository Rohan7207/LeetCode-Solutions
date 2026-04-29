// Problem: Unique Paths
// Link: https://leetcode.com/problems/unique-paths/
// Difficulty: Medium

// Approach:
// Use dynamic programming with a 1D array to optimize space.
// Create a dp array where each element stores
//     the number of ways to reach the current column.
// Initialize all values as 1 because the first row
//     can only be reached by moving right.
// Traverse the grid row by row starting from row 1:
//     - Update each cell using:
//           dp[j] = dp[j] + dp[j - 1]
//       where:
//           dp[j]     -> paths from top
//           dp[j - 1] -> paths from left
// The last element of the array contains
//     the total unique paths to the destination.

// Time Complexity: O(m * n)
// Space Complexity: O(n)


class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }

        return dp[n - 1];
    }
}
