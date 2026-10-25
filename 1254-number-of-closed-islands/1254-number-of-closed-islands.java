// Problem: Number of Closed Islands
// Link: https://leetcode.com/problems/number-of-closed-islands/?envType=daily-question&envId=2026-10-25
// Difficulty: Medium

// Approach:
// 1. Traverse every cell in the grid.
// 2. If a cell contains 0, it is unvisited land; start DFS.
// 3. In DFS:
//    a. If we go outside the grid, return false because the island touches the boundary.
//    b. If the cell is 1 (water or visited land), return true.
//    c. Mark the current land cell as 1 to prevent revisiting.
//    d. Explore all four directions using DFS.
//    e. Return true only if all four recursive results are true.
// 4. If DFS returns true, increment the closed island count.
// 5. Return the total count.

// Time Complexity: O(m * n)
// Space Complexity: O(m * n) worst case due to recursion stack


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

        boolean up = dfs(grid, row - 1, col);
        boolean down = dfs(grid, row + 1, col);
        boolean left = dfs(grid, row, col - 1);
        boolean right = dfs(grid, row, col + 1);

        return up && down && left && right;
    }
}
