// Problem: Surrounded Regions
// Link: https://leetcode.com/problems/surrounded-regions/
// Diificulty: Medium

// Approach:
// Any 'O' connected to the boundary cannot be surrounded.
// Traverse boundary rows and columns:
//     - Perform DFS for every boundary 'O'.
//     - Mark all connected safe 'O's as '#'.
// After marking:
//     - Convert remaining 'O' to 'X'
//       because they are surrounded.
//     - Convert '#' back to 'O'
//       since they are boundary-connected safe regions.
// Return the modified board.

// Time Complexity: O(m * n)
// Space Complexity: O(m * n) recursion stack


class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            dfs(board, 0, j);
            dfs(board, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == '#') board[i][j] = 'O';   
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != 'O')
            return;

        board[i][j] = '#';

        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }
}
