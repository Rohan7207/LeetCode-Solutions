class Solution {
    private int m, n;
    private int[][] dir = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        m = heights.length;
        n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        for (int i = 0; i < m; i++)
            dfs(i, 0, heights, pacific);
        for (int j = 0; j < n; j++)
            dfs(0, j, heights, pacific);
        for (int i = 0; i < m; i++)
            dfs(i, n - 1, heights, atlantic);
        for (int j = 0; j < n; j++)
            dfs(m - 1, j, heights, atlantic);

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    public void dfs(int row, int col, int[][] heights, boolean[][] vis) {
        if (vis[row][col]) return;

        vis[row][col] = true;

        for (int[] d : dir) {
            int x = row + d[0];
            int y = col + d[1];

            if (x < 0 || x >= m || y < 0 || y >= n)
                continue; 

            if (heights[x][y] < heights[row][col])
                continue;

            dfs(x, y, heights, vis);
        }
    }
}

