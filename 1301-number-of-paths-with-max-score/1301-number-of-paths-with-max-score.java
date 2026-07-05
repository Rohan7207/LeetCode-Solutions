// Problem: Number of Paths With Max Score
// Link: https://leetcode.com/problems/number-of-paths-with-max-score/?envType=daily-question&envId=2026-07-05
// Difficulty: Hard

// Approach:
// Since movement from 'E' is only allowed Down, Right and Diagonal,
// instead of moving from E → S,
// compute the answer in reverse from S → E.
// Define DP:
// dp[i][j][0] = Maximum score obtainable from cell (i,j) to S.
// dp[i][j][1] = Number of paths that obtain this maximum score.
// Initialization:
// • Every cell is initially unreachable,
//   so maximum score = -1.
// • Destination (S):
//      score = 0
//      paths = 1
// Process the grid from bottom-right to top-left.
// For every non-obstacle cell:
//     1. Look at the three reachable neighbours:
//            ↓  (i+1, j)
//            →  (i, j+1)
//            ↘  (i+1, j+1)
//     2. Ignore neighbours that are unreachable.
//     3. Choose the neighbour having the maximum score.
//     4. If multiple neighbours have the same maximum score,
//        add all their path counts.
//     5. Add the current cell's value to the maximum score
//        (except 'E' and 'S').
// Finally:
// • If dp[0][0] is reachable,
//   return {maximum score, number of paths}.
// • Otherwise,
//   no valid path exists, so return {0, 0}.

// Time Complexity: O(n²)
// Space Complexity: O(n²)


class Solution {
    private final int MOD = 1000000007;

    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int[][][] dp = new int[n][n][2]; 

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = -1;
            }
        }

        dp[n - 1][n - 1][0] = 0;
        dp[n - 1][n - 1][1] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (!(i == n - 1 && j == n - 1) && board.get(i).charAt(j) != 'X') {
                    update(dp, i, j, i + 1, j, n);

                    update(dp, i, j, i, j + 1, n);

                    update(dp, i, j, i + 1, j + 1, n);

                    if (dp[i][j][0] != -1) {
                        dp[i][j][0] += board.get(i).charAt(j) == 'E' ? 0 : board.get(i).charAt(j) - '0';
                    }
                }
            }
        }

        if (dp[0][0][0] != -1) {
            return new int[] { dp[0][0][0], dp[0][0][1] % MOD };
        }

        return new int[] { 0, 0 };
    }

    private void update(int[][][] dp, int x, int y, int u, int v, int n) {
        if (u >= n || v >= n || dp[u][v][0] == -1) {
            return;
        }

        if (dp[u][v][0] > dp[x][y][0]) {
            dp[x][y][0] = dp[u][v][0];
            dp[x][y][1] = dp[u][v][1];
        } else if (dp[u][v][0] == dp[x][y][0]) {
            dp[x][y][1] = (dp[x][y][1] + dp[u][v][1]) % MOD;
        }
    }
}
