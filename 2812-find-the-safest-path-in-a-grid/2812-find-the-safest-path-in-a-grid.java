// Problem: Find the Safest Path in a Grid
// Link: https://leetcode.com/problems/find-the-safest-path-in-a-grid/?envType=daily-question&envId=2026-07-02
// Difficulty: Medium

// Approach:
// The path's safeness factor is the minimum safety value
// among all cells in that path.
// So the solution is divided into two phases.
// Phase 1: Compute safety of every cell
// Every thief acts as a source.
// Perform Multi-source BFS:
//     - Push all thief cells into the queue.
//     - Their safety value is 0.
//     - Expand level by level.
//     - Every neighbour gets:
//           safety[neighbour] = safety[current] + 1
// Since BFS always visits the nearest source first,
// the first distance assigned to every cell is its
// minimum distance from any thief.
// Phase 2: Find the safest path
// Now every cell has a safety value.
// We need a path from (0,0) to (n-1,n-1)
// whose minimum safety value is maximum.
// Use a Max Heap.
// Every heap state stores:
//     {row, column, currentPathSafeness}
// currentPathSafeness = minimum safety encountered
// along the current path.
// Initially:
//     Push
//     {0, 0, safety[0][0]}
// Every time:
//     Pop the path having maximum current safeness.
// Explore all neighbours.
// For every neighbour:
//     newSafeness =
//         min(currentSafeness,
//             safety[neighbour])
// Push the neighbour with this updated safeness.
// The first time the destination is removed
// from the Max Heap,
// it is guaranteed to have the maximum possible
// safeness factor.

// Time Complexity: O(n² log n)
// Space Complexity: O(n²)


class Solution {
    int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();

        int[][] safety = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(safety[i], -1);
        }

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    q.offer(new int[] { i, j });
                    safety[i][j] = 0;
                }
            }
        }

        // Multi source BFS
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && safety[nr][nc] == -1) {
                    safety[nr][nc] = safety[r][c] + 1;

                    q.offer(new int[] { nr, nc });
                }
            }
        }

        // Max heap
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        boolean[][] vis = new boolean[n][n];

        pq.offer(new int[] {0, 0, safety[0][0]});
        vis[0][0] = true;

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();

            int r = curr[0];
            int c = curr[1];
            int safe = curr[2];

            if(r == n - 1 && c == n - 1) {
                return safe;
            }

            for(int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if(nr >= 0 && nr < n && nc >= 0 && nc < n && !vis[nr][nc]) {
                    int newSafe = Math.min(safe, safety[nr][nc]);

                    vis[nr][nc] = true;

                    pq.offer(new int[] {nr, nc, newSafe});
                }
            }
        }

        return 0;
    }
}
