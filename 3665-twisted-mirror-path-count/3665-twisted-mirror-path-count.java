// Problem: Twisted Mirror Path Count
// Link: https://leetcode.com/problems/twisted-mirror-path-count/?envType=daily-question&envId=2026-10-29
// Difficulty: Medium

// Approach:
// 1. Use 3D DP to track the number of ways to reach each cell.
//    - dp[row][col][left] = ways of entering from the left.
//    - dp[row][col][up] = ways of entering from above.
//    - Direction matters because a mirror changes the movement direction.
//
// 2. Initialize the starting cell.
//    - Set dp[0][0][left] = 1 as the initial state.
//
// 3. Prefill the first row.
//    - There is only one way to move horizontally.
//    - Continue until the first mirror, because it deflects
//      the path downward.
//
// 4. Prefill the first column.
//    - There is only one way to move vertically.
//    - Continue until the first mirror, because it deflects
//      the path to the right.
//
// 5. Fill the remaining DP states.
//    - To enter (row, col) from the left:
//      Check the mirror at (row, col - 1).
//      If it is a mirror, only paths entering that cell from above
//      can move right. Otherwise, both directions can move right.
//
//    - To enter (row, col) from above:
//      Check the mirror at (row - 1, col).
//      If it is a mirror, only paths entering that cell from the left
//      can move down. Otherwise, both directions can move down.
//
// 6. Apply modulo MOD while adding the number of ways.
//
// 7. Return the sum of both arrival directions at the destination,
//    since paths can reach it from either the left or above.

// Time Complexity: O(m * n)
// Space Complexity: O(m * n)


class Solution {

    private int MOD = 1000000007;
    private int left = 0; // Arrived at current cell from left cell(it means it was moving right)
    private int up = 1; // Arrived at current cell from up cell(it means it was moving down)

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // dp[row][col][left] = ways to reach this cell by entering from the left
        // dp[row][col][up] = ways to reach this cell by entering from the up
        int[][][] dp = new int[m][n][2];

        // Start cell : one way to be here (start is never a mirror, slot is arbitary)
        dp[0][0][left] = 1;

        // Prefill 0th row: one way to reach each cell, until first mirror
        for (int col = 1; col < n; col++) {
            dp[0][col][left] = 1;

            if (grid[0][col] == 1) {
                break; // Deflected down, cells beyond stay 0
            }
        }

        // Prefill 0th col: one way to reach each cell, until first mirror
        for (int row = 1; row < m; row++) {
            dp[row][0][up] = 1;

            if (grid[row][0] == 1) {
                break; // Deflected right, cells beyond stay 0
            }
        }

        // Fill the rest
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                // Entering from the left neighbour (row, col - 1)
                if (grid[row][col - 1] == 1) {
                    dp[row][col][left] = dp[row][col - 1][up]; // mirror: only UP-entrants turns right
                } else {
                    dp[row][col][left] = (dp[row][col - 1][up] + dp[row][col - 1][left]) % MOD;
                }

                // Entering from the up neighbour (row - 1, col)
                if (grid[row - 1][col] == 1) {
                    // mirror: only Left-entrants turns down
                    dp[row][col][up] = dp[row - 1][col][left];
                } else {
                    dp[row][col][up] = (dp[row - 1][col][up] + dp[row - 1][col][left]) % MOD;
                }
            }
        }

        // Destination is always empty: every arrivale counts, from either side
        return (dp[m - 1][n - 1][left] + dp[m - 1][n - 1][up]) % MOD;
    }
}
