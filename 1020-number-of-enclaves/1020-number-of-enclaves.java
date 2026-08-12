// Problem: Number of Enclaves
// Link: https://leetcode.com/problems/number-of-enclaves/
// Difficulty: Medium

// Approach:
// An enclave is a land cell (1) that cannot reach the boundary.
//
// Instead of finding every enclave individually, reverse the
// thinking:
//
// 1. Any land cell connected to the boundary can never be an
//    enclave because it can reach outside the grid.
//
// 2. Start DFS from every boundary land cell.
//
// 3. During DFS, mark every connected land cell as 0.
//    This removes all land that can reach the boundary.
//
// 4. After processing the boundary, any remaining 1 represents
//    an enclave.
//
// 5. Count all remaining 1s and return the count.
//
// The boundary is traversed using the four sides of the grid.
// The `top`, `bottom`, `left`, and `right` variables make sure
// each boundary cell is processed.

// Time Complexity: O(m × n)
// Space Complexity: O(m × n) in the worst case due to DFS recursion.


class Solution {
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;

        // Top row
        for (int i = left; i <= right; i++) {
            dfs(grid, top, i);
        }
        top++;

        // Right column
        for (int i = top; i <= bottom; i++) {
            dfs(grid, i, right);
        }
        right--;

        // Bottom row (if rows remain)
        if (top <= bottom) {
            for (int i = right; i >= left; i--) {
                dfs(grid, bottom, i);
            }
            bottom--;
        }

        // Left column (if columns remain)
        if (left <= right) {
            for (int i = bottom; i >= top; i--) {
                dfs(grid, i, left);
            }
            left++;
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void dfs(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0) {
            return;
        }

        grid[row][col] = 0;
        dfs(grid, row + 1, col);  // Bottom
        dfs(grid, row - 1, col);  // Top
        dfs(grid, row, col + 1);  // Right
        dfs(grid, row, col - 1);  // Left
    }
}
