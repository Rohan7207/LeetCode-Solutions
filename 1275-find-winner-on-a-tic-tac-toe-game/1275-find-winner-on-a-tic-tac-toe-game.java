// Problem: Find Winner on a Tic Tac Toe Game
// Link: https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/
// Difficulty: Easy

// Approach:
// Use Simulation + Board Checking.
//
// 1. Create a 3 x 3 board.
//
// 2. Process every move in order.
//    Since A always moves first:
//
//       even index → A → store 1
//       odd index  → B → store 2
//
// 3. After all moves are placed, check the board for a winner.
//
// 4. Check all 3 rows.
//    If all three cells are equal and non-zero,
//    that player wins.
//
// 5. Check all 3 columns using the same logic.
//
// 6. Check the 2 diagonals:
//
//       [0][0] → [1][1] → [2][2]
//       [0][2] → [1][1] → [2][0]
//
// 7. If a winning line contains:
//       1 → return "A"
//       2 → return "B"
//
// 8. If nobody wins:
//       9 moves → "Draw"
//       fewer than 9 moves → "Pending"

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public String tictactoe(int[][] moves) {
        int[][] board = new int[3][3];

        for (int i = 0; i < moves.length; i++) {
            int row = moves[i][0];
            int col = moves[i][1];

            if (i % 2 == 0) {
                board[row][col] = 1;
            } else {
                board[row][col] = 2;
            }
        }

        // Row checking
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0] == 1 ? "A" : "B";
            }
        }

        // Column checking
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i] == 1 ? "A" : "B";
            }
        }

        // Diagonal Checking
        if ((board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[1][1] != 0 && board[1][1] == board[0][2] && board[1][1] == board[2][0])) {
            return board[1][1] == 1 ? "A" : "B";
        }

        if (moves.length == 9) {
            return "Draw";
        }

        return "Pending";
    }
}
