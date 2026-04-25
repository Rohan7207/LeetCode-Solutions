class Solution {
    public void solveSudoku(char[][] board) {
        helper(board, 0, 0);
    }

    public boolean issafe(char[][] board, int row, int col, int number) {
        //row and column condition
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == (char) (number + '0')) {
                return false;
            }
            if (board[row][i] == (char) (number + '0')) {
                return false;
            }
        }

        //matrix
        int starting_row = (row / 3) * 3;
        int starting_col = (col / 3) * 3;

        for (int i = starting_row; i < starting_row + 3; i++) {
            for (int j = starting_col; j < starting_col + 3; j++) {
                if (board[i][j] == (char) (number + '0')) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean helper(char[][] board, int row, int col) {
        if (row == board.length) {
            return true;
        }
        
        int nrow = 0;
        int ncol = 0;
        if (col != board.length - 1) {
            nrow = row;
            ncol = col + 1;
        } else {
            nrow = row + 1;
            ncol = 0;
        }

        if (board[row][col] != '.') {
            if (helper(board, nrow, ncol)) {
                return true;
            }
        } else {
            for (int i = 1; i <= 9; i++) {
                if (issafe(board, row, col, i)) {
                    board[row][col] = (char) (i + '0');
                    if (helper(board, nrow, ncol)) {
                        return true;
                    } else {
                        board[row][col] = '.';
                    }
                }
            }
        }
        return false;
    }
}