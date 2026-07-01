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

        int high = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                high = Math.max(high, safety[i][j]);
            }
        }

        int low = 0;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canReach(safety, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean canReach(int[][] safety, int limit) {
        int n = safety.length;

        if (safety[0][0] < limit) {
            return false;
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][n];

        q.offer(new int[] { 0, 0 });
        vis[0][0] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];

            if (r == n - 1 && c == n - 1) {
                return true;
            }

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !vis[nr][nc] && safety[nr][nc] >= limit) {
                    vis[nr][nc] = true;

                    q.offer(new int[] { nr, nc });
                }
            }
        }

        return false;
    }
}