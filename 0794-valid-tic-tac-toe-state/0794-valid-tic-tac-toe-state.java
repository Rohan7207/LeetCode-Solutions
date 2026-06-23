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