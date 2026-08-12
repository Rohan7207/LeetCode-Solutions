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
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void dfs(int[][] grid, int row, int col) {
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0) {
            return;
        }

        grid[row][col] = 0;
        dfs(grid, row + 1, col);
        dfs(grid, row - 1, col);
        dfs(grid, row, col + 1);
        dfs(grid, row, col - 1);
    }
}