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

/*
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
*/

// Time Complexity: O(m * n)
// Space Complexity: O(m * n) in the worst case because of DFS recursion.

// Use DFS to explore each island completely; if any part of the island reaches outside the grid, it is not closed. Count it only when the entire DFS stays inside.

/*
    🔑 Key Observation
0 → land → explore
1 → water → valid boundary
outside grid → not closed
Any path → outside → false
All paths → inside → true
✨ Magic Line
if (!(dfs(grid, nrow, ncol))) {
    isClosed = false;
}

One false is enough to make the entire island not closed.

⚠️ Important
grid[row][col] = 1;

Mark the land as visited so the DFS doesn't repeatedly visit the same cells.

if (grid[i][j] == 0 && dfs(grid, i, j)) {
    count++;
}

Count only when DFS confirms the whole island is closed.

🧩 Pattern Recognition
Grid DFS
Connected Components
Boundary Detection
Visited marking
Boolean DFS

📚 Similar Questions
LeetCode 200 — Number of Islands
LeetCode 463 — Island Perimeter
LeetCode 1020 — Number of Enclaves
LeetCode 130 — Surrounded Regions
LeetCode 1254 — Number of Closed Islands
*/