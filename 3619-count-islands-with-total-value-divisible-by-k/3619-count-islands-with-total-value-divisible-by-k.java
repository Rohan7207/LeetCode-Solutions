// Problem: Count Islands With Total Value Divisible by K
// Link: https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k/?envType=daily-question&envId=2026-10-26
// Difficulty: Medium

// Approach:
// 1. Initialize the grid dimensions m and n.
// 2. Traverse every cell in the grid.
// 3. If grid[i][j] != 0, an unvisited island has been found:
//    a. Start DFS from that cell.
//    b. Add every connected land cell's value to sum.
//    c. Mark each visited cell as 0 to prevent revisiting.
// 4. After DFS finishes, check whether sum % k == 0.
//    a. If true, increment count.
//    b. Reset sum to 0 for the next island.
// 5. Return count.

// Time Complexity: O(m * n)
// Auxiliary Space: O(m * n) worst case due to recursive call stack


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
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
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
