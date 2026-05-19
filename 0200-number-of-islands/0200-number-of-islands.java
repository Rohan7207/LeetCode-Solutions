// Problem: Number of Islands
// Link: https://leetcode.com/problems/number-of-islands/
// Difficulty: Medium

// Approach:
// Traverse every cell of the grid.
// Whenever a land cell ('1') is found,
// increment island count and start DFS.
// DFS visits all connected land cells
// in four directions and marks them as visited
// by converting '1' to '0'.
// This ensures the same island is not counted again.
// Continue traversal until entire grid is processed.
// Return total island count.

// Time Complexity: O(m * n)
// Space Complexity: O(m * n) // recursion stack in worst case


class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int r, int c) {
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';

        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}
