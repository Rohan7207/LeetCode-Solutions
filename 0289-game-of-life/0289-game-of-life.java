class Solution {
    public void gameOfLife(int[][] board) {

        //Neighbours array to find neighbouring cells for given cell
        int[] neigh = { 0, 1, -1 };

        int rows = board.length;
        int cols = board[0].length;

        //Iterate through board
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                //for each cell count no.of live neighbours
                int lives = 0;

                //It runs in constant time
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (!(neigh[i] == 0 && neigh[j] == 0)) {
                            int row = (r + neigh[i]);
                            int col = (c + neigh[j]);

                            //Check the validity of neigh cell
                            //and whether it was originally a live cell.
                            if ((row < rows && row >= 0) && (col < cols && col >= 0)
                                    && (Math.abs(board[row][col]) == 1)) {
                                lives++;
                            }
                        }
                    }
                }

                //Rule 1 or 3
                if ((board[r][c] == 1) && (lives < 2 || lives > 3)) {
                    // -1 signifies cell is now dead but originally was live
                    board[r][c] = -1;
                }

                //Rule 4
                if (board[r][c] == 0 && lives == 3) {
                    // 2 signifies cell is now live but originally dead
                    board[r][c] = 2;
                }
            }
        }

        //Get final representation for newly updated board
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] > 0) {
                    board[r][c] = 1; //Here the rule 2 comes
                } else {
                    board[r][c] = 0;
                }
            }
        }
    }
}

//Rules 
/* Current state   rule of neigh   next state
1.   1                  < 2           0
2.   1                  2 to 3        1
3.   1                  > 3           0
4.   0                    3(lives)    1

1st approach with O(m*n) and O(m*n)
In these we make copy of board and make check changes in original board
and check the conditions in copied board, but space is more used.

2nd approach with O(m*n) and O(1)
we change the rules in next state
alive to dead, 1 to -1
dead to alive, 0 to 2
so when returning the board we convert all -1 to 0 and 2 to 1
rules 1. -1  2. 1  3. -1  4. 2
*/
