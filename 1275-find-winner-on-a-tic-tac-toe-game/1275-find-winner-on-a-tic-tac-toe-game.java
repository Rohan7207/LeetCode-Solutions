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

/*
     int count = 0;
        int[] rows = new int[3];
        int[] cols = new int[3];
        int diagonal = 0;
        int antiDiagonal = 0;
        boolean playerA = true;
        int res = 0;

        for (int[] move : moves) {
            int row = move[0];
            int col = move[1];
            int value = playerA ? 1 : -1;

            rows[row] += value;
            cols[col] += value;

            if (row == col) {
                diagonal += value;
            }

            if (row + col == 2) {
                antiDiagonal += value;
            }

            if (Math.abs(rows[row]) == 3 ||
                    Math.abs(cols[col]) == 3 ||
                    Math.abs(diagonal) == 3 ||
                    Math.abs(antiDiagonal) == 3) {
                res = value;
            }

            playerA = !playerA;

            count++;
        }

        if (res == 0) {
            if (count == 9) {
                return "Draw";
            } else {
                return "Pending";
            }
        }

        return res == 1 ? "A" : "B";
*/