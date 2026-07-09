// Problem: Rotting Oranges
// Link: https://leetcode.com/problems/rotting-oranges/
// Difficulty: Medium

// Approach:
// Every rotten orange spreads the infection to its
// four adjacent fresh oranges in exactly one minute.
// Since all rotten oranges spread simultaneously,
// this is a Multi-Source BFS problem.
// First, traverse the grid:
//     • Count the total number of fresh oranges.
//     • Add every initially rotten orange into
//       the BFS queue.
// If there are no fresh oranges,
// return 0 immediately.
// Perform BFS level by level.
// Each level represents one minute.
// For every rotten orange in the current level:
//     • Visit its four neighboring cells.
//     • If a neighboring cell contains a fresh orange:
//           - Turn it rotten.
//           - Decrease the fresh orange count.
//           - Push it into the queue.
// After processing one complete BFS level,
// increment the minute count.
// Continue until the queue becomes empty.
// Finally:
//     • If every fresh orange became rotten,
//       return the total minutes.
//     • Otherwise,
//       some fresh oranges were unreachable,
//       so return -1.
// Since minutes is incremented even after the
// last level, subtract one before returning.

// Time Complexity: O(m × n)
// Space Complexity: O(m × n)

class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0)
            return -1;

        int m = grid.length;
        int n = grid[0].length;
        int freshoranges = 0;
        Queue<int[]> rottenQueue = new LinkedList<>();

        //Count the fresh oranges and add rotten oranges to queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    freshoranges++;
                } else if (grid[i][j] == 2) {
                    rottenQueue.offer(new int[] { i, j });
                }
            }
        }

        if (freshoranges == 0)
            return 0; //No fresh oranges

        int minutes = 0;
        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        //BFS
        while (!rottenQueue.isEmpty()) {
            int size = rottenQueue.size();
            for (int i = 0; i < size; i++) {
                int[] rotten = rottenQueue.poll();
                for (int[] dir : directions) {
                    int x = rotten[0] + dir[0];
                    int y = rotten[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2; //turn fresh orange into rotten
                        freshoranges--;
                        rottenQueue.offer(new int[] { x, y });
                    }
                }
            }
            minutes++; //Increment time after each level of bfs
        }

        return freshoranges == 0 ? minutes - 1 : -1; //Adjust extra increment
    }
}
