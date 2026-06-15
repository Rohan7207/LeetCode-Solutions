class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxarea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    maxarea = Math.max(maxarea, area);
                }
            }
        }

        return maxarea;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0; //Mark cell as visited;
        int area = 1; //Current cell

        //Explore all 4 directions
        area += dfs(grid, i - 1, j); //up
        area += dfs(grid, i + 1, j); //down
        area += dfs(grid, i, j - 1); //left
        area += dfs(grid, i, j + 1); //right

        return area;
    }
}
