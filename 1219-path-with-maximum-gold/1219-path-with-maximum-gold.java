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