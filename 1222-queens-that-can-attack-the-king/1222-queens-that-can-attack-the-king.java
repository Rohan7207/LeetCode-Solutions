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