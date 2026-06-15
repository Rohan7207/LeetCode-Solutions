// Problem: Max Area of Island
// Link: https://leetcode.com/problems/max-area-of-island/
// Difficulty: Medium

// Approach:
// We need to find the largest island area
// in the grid.
// An island is formed by connected 1's
// (up, down, left, right).
// Traverse every cell in the grid.
// If a cell contains 1:
//     - Start DFS from that cell
//     - Calculate the entire island area
//     - Update maximum area found
// In DFS:
//     - If cell is out of bounds
//       or water (0):
//           return 0
//     - Mark current land cell as visited
//       by changing it to 0
//     - Count current cell:
//           area = 1
//     - Explore all 4 directions
//     - Add their returned areas
// Return total area of the current island.
// Finally return the maximum island area.

// Time Complexity: O(m * n)
// Space Complexity: O(m * n) (recursion stack worst case)


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

        grid[i][j] = 0; 
        int area = 1;

        //Explore all 4 directions
        area += dfs(grid, i - 1, j);
        area += dfs(grid, i + 1, j);
        area += dfs(grid, i, j - 1); 
        area += dfs(grid, i, j + 1); 

        return area;
    }
}
