// Problem: Find the Safest Walk Through a Grid
// Link: https://leetcode.com/problems/find-a-safe-walk-through-a-grid/?envType=daily-question&envId=2026-07-02
// Difficulty: Medium

// Approach:
// The health decreases only when entering an unsafe cell (value = 1).
// A state is represented by:
//     {row, column, remainingHealth}
// The same cell can be reached with different remaining health.
// Example:
//     Cell (2,3)
//     Path A -> Health = 2
//     Path B -> Health = 5
// Path B is always better because it leaves us with
// more health for future moves.
// 
// Step 1:
// Handle the starting cell.
// If the starting cell is unsafe,
// decrease health by 1.
// If health becomes 0 or less,
// reaching the destination is impossible.
//
// Step 2:
// Maintain a bestHealth matrix.
// bestHealth[r][c] stores:
//     Maximum remaining health with which
//     we have reached cell (r,c).
// Initialize every cell with -1.
// Store the starting health:
//     bestHealth[0][0] = health
//
// Step 3:
// Perform BFS.
// Queue stores:
//     {row, column, remainingHealth}
// Pop one state at a time.
//
// Step 4:
// Explore all four neighbours.
// Compute:
//     newHealth = currentHealth - grid[nr][nc]
// If:
//     newHealth > 0
// and
//     newHealth > bestHealth[nr][nc]
// then:
//     - Update bestHealth.
//     - Push the neighbour into the queue.
// This ensures we only continue exploring
// if we reach a cell with more remaining health
// than before.
//
// Step 5:
// If the destination is reached,
// return true.
// If BFS finishes without reaching it,
// return false.

// Time Complexity: O(m × n × health) (worst case)
// Space Complexity: O(m × n)


class Solution {
    int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        if (grid.get(0).get(0) == 1) {
            health--;
        }

        if (health <= 0) return false;

        int[][] bestHealth = new int[m][n];

        for (int[] row : bestHealth) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { 0, 0, health });
        bestHealth[0][0] = health;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];
            int currHealth = curr[2];

            if (r == m - 1 && c == n - 1) {
                return true;
            }

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int newHealth = currHealth - grid.get(nr).get(nc);

                    if (newHealth > 0 && newHealth > bestHealth[nr][nc]) {
                        bestHealth[nr][nc] = newHealth;
                        q.offer(new int[] { nr, nc, newHealth });
                    }
                }
            }
        }

        return false;
    }
}
