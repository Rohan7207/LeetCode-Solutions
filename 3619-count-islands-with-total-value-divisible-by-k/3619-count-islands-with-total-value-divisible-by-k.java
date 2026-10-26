class Solution {

    private long sum = 0;
    private int m, n;

    public int countIslands(int[][] grid, int k) {
        int count = 0;
        m = grid.length;
        n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    dfs(grid, i, j);

                    if (sum % k == 0) {
                        count++;
                    }

                    sum = 0;
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, int r, int c) {
        if(r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return;
        }

        sum += grid[r][c];
        grid[r][c] = 0;

        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
}

/*
    class Solution {

    int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    long sum;

    public int countIslands(int[][] grid, int k) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    sum = 0;
                    dfs(grid, i, j);

                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, int r, int c) {
        sum += grid[r][c];
        grid[r][c] = 0;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] != 0) {
                dfs(grid, nr, nc);
            }
        }
    }
}
*/