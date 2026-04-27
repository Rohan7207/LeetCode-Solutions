// Problem: N-Queens
// Link: https://leetcode.com/problems/n-queens/
// Difficulty: Hard

// Approach:
// Use backtracking to place queens column by column.
// For each column, try placing a queen in every row.
// Before placing a queen, check whether the position is valid:
//     - No queen exists in the same row on the left side.
//     - No queen exists in the upper-left diagonal.
//     - No queen exists in the lower-left diagonal.
// If the position is valid:
//     - Place the queen.
//     - Recursively move to the next column.
//     - Backtrack by removing the queen.
// When all columns are filled, construct the board
// configuration and add it to the result list.

// Time Complexity: O(N!)
// Space Complexity: O(N²)


class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        //Construct the board filled with . 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        List<List<String>> res = new ArrayList<>();
        backtrack(board, 0, res);
        return res;
    }

    private void backtrack(char[][] board, int col, List<List<String>> res) {
        //Base case
        if (col == board.length) {
            res.add(construct(board));
            return;
        }

        for (int i = 0; i < board.length; i++) {
            if (isValid(board, i, col)) {
                board[i][col] = 'Q';
                backtrack(board, col + 1, res);
                board[i][col] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        // Check queens on left side of row
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 'Q') {
                return false;
            }
        }

        //Upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        //lower left diagonal
        for (int i = row, j = col; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private List<String> construct(char[][] board) {
        List<String> result = new ArrayList<>();
        //Construct the board as the value present in board like ..Q.
        for (int i = 0; i < board.length; i++) {
            String row = new String(board[i]);
            result.add(row);
        }
        return result;
    }
}
