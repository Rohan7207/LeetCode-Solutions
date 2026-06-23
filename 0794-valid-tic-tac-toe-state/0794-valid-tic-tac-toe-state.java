// Problem: Valid Tic-Tac-Toe State
// Link: https://leetcode.com/problems/valid-tic-tac-toe-state/
// Difficulty: Medium

// Approach:
// We need to check whether the given Tic-Tac-Toe board can come from a valid game.
// In Tic-Tac-Toe:
//     X always plays first.
//     Then O plays.
//     Then X, then O, and so on.
// So the count of X and O must follow:
//     xCount == oCount
//     OR
//     xCount == oCount + 1
// If this condition fails, the board is invalid.
// After count validation, we check whether X has won or O has won.
// A player wins if they have 3 same characters in:
//     any row
//     any column
//     any diagonal
// If both X and O win, the board is invalid.
// Because the game stops immediately once one player wins.
// If X wins:
//     X must have played the last move.
//     So xCount must be oCount + 1.
// If O wins:
//     O must have played the last move.
//     So xCount must be equal to oCount.
// If nobody wins:
//     The board is valid as long as move counts are valid.

// Time Complexity: O(1)
// Space Complexity: O(1)


class Solution {
    public boolean validTicTacToe(String[] board) {
        int xCount = 0;
        int oCount = 0;

        for (String move : board) {
            for (int i = 0; i < move.length(); i++) {
                char ch = move.charAt(i);

                if (ch == 'X') {
                    xCount++;
                } else if (ch == 'O') {
                    oCount++;
                }
            }
        }

        if (xCount != oCount && xCount != oCount + 1)
            return false;

        boolean xWin = win(board, 'X');
        boolean oWin = win(board, 'O');

        if (xWin && oWin) {
            return false;
        } else if (xWin) {
            return xCount == oCount + 1;
        } else if (oWin) {
            return xCount == oCount;
        }

        return true;
    }

    private boolean win(String[] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == player &&
                    board[i].charAt(1) == player &&
                    board[i].charAt(2) == player) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0].charAt(j) == player &&
                    board[1].charAt(j) == player &&
                    board[2].charAt(j) == player) {
                return true;
            }
        }

        if (board[0].charAt(0) == player &&
                board[1].charAt(1) == player &&
                board[2].charAt(2) == player) {
            return true;
        }

        if (board[0].charAt(2) == player &&
                board[1].charAt(1) == player &&
                board[2].charAt(0) == player) {
            return true;
        }

        return false;
    }
}
