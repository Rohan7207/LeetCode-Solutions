class Solution {
    public int swimInWater(int[][] grid) {
        //We use binary search from 0 <= t <= max in matrix
        //We use dfs

        int n = grid.length;
        int left = grid[0][0];
        int right = n * n - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canSwim(grid, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canSwim(int[][] grid, int t) {
        int n = grid.length;
        boolean[][] vis = new boolean[n][n];
        return dfs(grid, vis, 0, 0, t);
    }

    private boolean dfs(int[][] grid, boolean[][] vis, int i, int j, int t) {
        int n = grid.length;
        if (i < 0 || i >= n || j < 0 || j >= n || vis[i][j] || grid[i][j] > t) {
            return false;
        }

        if (i == n - 1 && j == n - 1) {
            return true;
        }

        vis[i][j] = true;
        return dfs(grid, vis, i - 1, j, t) ||
                dfs(grid, vis, i + 1, j, t) ||
                dfs(grid, vis, i, j - 1, t) ||
                dfs(grid, vis, i, j + 1, t);
    }
}