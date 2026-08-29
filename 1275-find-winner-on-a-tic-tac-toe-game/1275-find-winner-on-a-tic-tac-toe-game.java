class Solution {
    public String tictactoe(int[][] moves) {
        int count = 0;
        int[] rows = new int[3];
        int[] cols = new int[3];
        int diagonal = 0;
        int antiDiagonal = 0;
        boolean playerA = true;
        int res = 0;

        for(int[] move : moves) {
            int row = move[0];
            int col = move[1];
            int value = playerA ? 1 : -1;

            rows[row] += value;
            cols[col] += value;

            if(row == col) {
                diagonal += value;
            } 

            if(row + col == 2) {
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

        if(res == 0) {
            if(count == 9) {
                return "Draw";
            } else {
                return "Pending";
            }
        }


        return res == 1 ? "A" : "B";
    }
}