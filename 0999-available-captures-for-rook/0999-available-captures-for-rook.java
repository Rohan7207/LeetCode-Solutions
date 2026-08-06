class Solution {

    private int sum = 0;

    public int numRookCaptures(char[][] board) {
        // Find Rook's position
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    // Explore all four directions
                    dfs(board, i, j - 1, 'L');
                    dfs(board, i, j + 1, 'R');
                    dfs(board, i - 1, j, 'U');
                    dfs(board, i + 1, j, 'D');
                }
            }

        }
        return sum;
    }

    private void dfs(char[][] board, int i, int j, char ch) {
        // Stop if out of bounds or blocked by a bishop
        if (i < 0 || i >= 8 || j < 0 || j >= 8 || board[i][j] == 'B') {
            return;
        }

        // If a pawn is found, capture it and stop further movement
        if (board[i][j] == 'p') {
            sum++;
            return;
        }

        // Continue in the same direction
        if (ch == 'L')
            dfs(board, i, j - 1, 'L');
        if (ch == 'R')
            dfs(board, i, j + 1, 'R');
        if (ch == 'U')
            dfs(board, i - 1, j, 'U');
        if (ch == 'D')
            dfs(board, i + 1, j, 'D');
    }
}