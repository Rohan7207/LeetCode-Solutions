// Problem: Queens That Can Attack the King
// Link: https://leetcode.com/problems/queens-that-can-attack-the-king/
// Difficulty: Medium

// Approach:
// Use a boolean board + DFS in 8 directions.
//
// 1. Create an 8 x 8 boolean board because the chessboard is
//    always fixed at 8 x 8.
//
// 2. Mark every queen's position as true:
//
//      board[row][col] = true
//
// 3. From the king, there are only 8 possible directions:
//
//      up, down, left, right,
//      top-left, top-right,
//      bottom-left, bottom-right
//
// 4. Start one DFS call for each direction.
//
// 5. In DFS, move exactly one step using:
//
//      row += dr
//      col += dc
//
// 6. If the position is outside the board, stop.
//
// 7. If a queen is found:
//      - Add its position to the answer.
//      - Stop this direction.
//
//    We stop because this is the closest queen in that direction;
//    another queen behind it is blocked.
//
// 8. If there is no queen, recursively continue in the same
//    direction.

// Time Complexity: O(8 * 8) = O(1)
// Space Complexity: O(8 * 8) = O(1) for the board, plus O(8) recursion depth.


class Solution {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[][] board = new boolean[8][8];

        for (int[] queen : queens) {
            board[queen[0]][queen[1]] = true;
        }

        int[][] directions = {
                { -1, 0 }, // up
                { 1, 0 }, // down
                { 0, -1 }, // left
                { 0, 1 }, // right
                { -1, -1 }, // top-left
                { -1, 1 }, // top-right
                { 1, -1 }, // down-left
                { 1, 1 } // down-right
        };

        for (int[] dir : directions) {
            dfs(board, king[0], king[1], dir[0], dir[1], ans);
        }

        return ans;
    }

    private void dfs(boolean[][] board, int row, int col, int dr, int dc, List<List<Integer>> ans) {
        row += dr;
        col += dc;

        if (row < 0 || row >= 8 || col < 0 || col >= 8) {
            return;
        }

        if (board[row][col]) {
            ans.add(Arrays.asList(row, col));
            return;
        }

        dfs(board, row, col, dr, dc, ans);
    }
}
