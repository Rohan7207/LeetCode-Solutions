// Problem: Number of Closed Islands
// Link: https://leetcode.com/problems/number-of-closed-islands/
// Difficulty: Medium

// Approach:
// Use DFS to explore each island and determine whether it is closed.
//
// 1. Traverse every cell in the grid.
//
// 2. When we find a land cell (0), start a DFS.
//
// 3. During DFS, mark every visited land cell as 1.
//    This prevents visiting the same island again.
//
// 4. For every direction (up, down, left, right), continue DFS.
//
// 5. The important part:
//    If DFS moves outside the grid, return false.
//
//       outside grid → island touches boundary → NOT closed
//
//    If DFS reaches water (1), return true because water
//    does not make the island open.
//
// 6. Combine the results of all four directions.
//
//    If ANY direction returns false,
//    the entire island is not closed.
//
// 7. If DFS returns true for the starting cell,
//    the entire island is surrounded by water and does not
//    touch the boundary, so increment count.

// Time Complexity: O(m * n)
// Space Complexity: O(m * n) in the worst case because of DFS recursion.


class Solution {
    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && dfs(grid, i, j)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean dfs(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return false;
        }

        if (grid[row][col] == 1) {
            return true;
        }

        grid[row][col] = 1;
        boolean isClosed = true;

        int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int[] dir : dirs) {
            int nrow = row + dir[0];
            int ncol = col + dir[1];

            if (!(dfs(grid, nrow, ncol))) {
                isClosed = false;
            }
        }

        return isClosed;
    }
}
