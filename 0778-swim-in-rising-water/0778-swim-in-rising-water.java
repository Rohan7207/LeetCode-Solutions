// Problem: Siwiming in Rising Water
// Link: https://leetcode.com/problems/swim-in-rising-water/
// Difficulty: Hard

// Approach:
// We need to find the minimum time t
// at which we can move from top-left cell
// to bottom-right cell.
// At time t:
//     We can only enter cells where:
//         grid[i][j] <= t
// So for any fixed time t,
// we can check whether reaching the destination is possible
// using DFS.
// This helper function is:
//     canSwim(grid, t)
// It returns true if we can reach bottom-right
// using only cells whose value is <= t.
// Now observe:
//     If we can reach destination at time t,
//     then we can also reach it at any time greater than t.
// So the answer follows a monotonic pattern:
//     false false false true true true
// Therefore, use Binary Search on time.
// Binary Search:
//     left  = grid[0][0]
//     right = n * n - 1
// For each mid:
//     If canSwim(mid) is true:
//         mid is possible
//         try smaller time
//         right = mid
//     Else:
//         mid is too small
//         need more time
//         left = mid + 1
// Finally, left will be the minimum time
// required to reach the destination.
// DFS:
//     Start from (0, 0)
//     Stop if:
//         cell is outside grid
//         cell is already visited
//         grid[i][j] > t
//     If destination is reached:
//         return true
//     Otherwise:
//         mark current cell visited
//         try all 4 directions

// Time Complexity: O(n^2 log(n^2))
// Space Complexity: O(n^2)


class Solution {
    public int swimInWater(int[][] grid) {
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
