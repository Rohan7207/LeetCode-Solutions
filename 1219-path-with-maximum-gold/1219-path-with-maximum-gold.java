// Problem: Path with Maximum Gold
// Link: https://leetcode.com/problems/path-with-maximum-gold/
// Difficulty: Medium

// Approach:
// Use DFS + Backtracking.
//
// 1. Start DFS from every non-zero cell because the path can
//    start from any cell containing gold.
//
// 2. In `helper()`:
//    - If the cell is outside the grid or contains 0,
//      return 0.
//
// 3. Store the current cell's gold:
//
//      int gold = grid[row][col];
//
// 4. Mark the current cell as 0.
//    This means the cell is visited and cannot be used again
//    in the current path.
//
// 5. Try all 4 possible directions:
//
//      down
//      up
//      right
//      left
//
//    These are alternative paths, so take only the maximum:
//
//      best = max(all four directions)
//
// 6. Restore the original gold value.
//    This is the backtracking step and allows another DFS path
//    to use this cell.
//
// 7. Return:
//
//      current gold + best path from this cell

// Time Complexity: O(m * n * 4^(m*n)) in the worst case
// Space Complexity: O(m*n) for the recursion stack


class Solution {
    public int getMaximumGold(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    ans = Math.max(ans, helper(grid, i, j));
                }
            }
        }

        return ans;
    }

    private int helper(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0) {
            return 0;
        }

        int gold = grid[row][col];

        // Mark as visited
        grid[row][col] = 0;
        int best = 0;

        best = Math.max(best, helper(grid, row + 1, col)); // down
        best = Math.max(best, helper(grid, row - 1, col)); // up
        best = Math.max(best, helper(grid, row, col + 1)); //right
        best = Math.max(best, helper(grid, row, col - 1)); //left

        // Restore / Backtrack
        grid[row][col] = gold;

        return gold + best;
    }
}
