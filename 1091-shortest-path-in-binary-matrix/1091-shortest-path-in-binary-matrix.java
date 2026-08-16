// Problem : Shortest Path in Binary Matrix
// Link : https://leetcode.com/problems/shortest-path-in-binary-matrix/
// Difficulty : Medium

// Approach:
// Use BFS because every move from one cell to another has the same cost (1).
// Therefore, BFS guarantees that the first time we reach the destination,
// we have found the shortest clear path.
//
// 1. Check whether the starting or ending cell is blocked.
//    If either is `1`, return -1.
//
// 2. Use a queue to store:
//       {row, column, distance}
//
//    Start from (0, 0) with distance 1 because the starting cell
//    itself is counted in the path length.
//
// 3. From every cell, explore all 8 possible directions because
//    diagonal movement is allowed.
//
// 4. If a neighboring cell is inside the grid and contains 0:
//       - Mark it as visited by changing it to 1.
//       - Add it to the queue with distance + 1.
//
// 5. When the destination `(n-1, n-1)` is removed from the queue,
//    return its distance.
//
// 6. If the queue becomes empty without reaching the destination,
//    no clear path exists, so return -1.

// Time Complexity: O(n²)
// Space Complexity: O(n²)


class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { 0, 0, 1 });

        grid[0][0] = 1;

        int[][] dirs = {
                { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }
        };

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int row = curr[0];
            int col = curr[1];
            int dist = curr[2];

            if (row == n - 1 && col == n - 1) {
                return dist;
            }

            for (int[] dir : dirs) {
                int nrow = row + dir[0];
                int ncol = col + dir[1];

                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < n && grid[nrow][ncol] == 0) {
                    grid[nrow][ncol] = 1;
                    q.offer(new int[] { nrow, ncol, dist + 1 });
                }
            }
        }

        return -1;
    }
}
