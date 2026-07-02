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