// Problem: Game of Life
// Link: https://leetcode.com/problems/game-of-life/
// Difficulty: Medium

// Approach:
// Update the board in-place using temporary states.
// For every cell, count its live neighbours
// by checking all 8 surrounding cells.
//
// Use special marker values:
//     -1 means cell was originally live
//        but becomes dead.
//      2 means cell was originally dead
//        but becomes live.
//
// While counting live neighbours,
// use Math.abs(board[row][col]) == 1
// so originally live cells are counted
// even if they were changed to -1.
//
// Apply the Game of Life rules:
//     - Live cell dies if live neighbours < 2 or > 3.
//     - Dead cell becomes live if live neighbours == 3.
//     - Other cells remain unchanged.
//
// Finally, convert temporary states:
//     positive values become 1
//     zero or negative values become 0.

// Time Complexity: O(m * n)
// Space Complexity: O(1)


class Solution {
    public void gameOfLife(int[][] board) {
        int[] neigh = { 0, 1, -1 };

        int rows = board.length;
        int cols = board[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int lives = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (!(neigh[i] == 0 && neigh[j] == 0)) {
                            int row = (r + neigh[i]);
                            int col = (c + neigh[j]);

                            if ((row < rows && row >= 0) && (col < cols && col >= 0)
                                    && (Math.abs(board[row][col]) == 1)) {
                                lives++;
                            }
                        }
                    }
                }

                if ((board[r][c] == 1) && (lives < 2 || lives > 3)) {
                    board[r][c] = -1;
                }

                if (board[r][c] == 0 && lives == 3) {
                    board[r][c] = 2;
                }
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] > 0) {
                    board[r][c] = 1;
                } else {
                    board[r][c] = 0;
                }
            }
        }
    }
}
