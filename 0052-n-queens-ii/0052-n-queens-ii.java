// Problem: N-Queens II
// Link: https://leetcode.com/problems/n-queens-ii/
// Difficulty: Hard

// Approach:
// Use backtracking to place one queen in each row.
// Track occupied columns and diagonals using boolean arrays:
//     - cols[col] → checks whether a column already has a queen.
//     - d1[row + col] → checks anti-diagonals (/).
//     - d2[row - col + n] → checks main diagonals (\).
// For each row, try placing a queen in every column:
//     - Skip positions where column or diagonals are occupied.
//     - Mark the column and diagonals as occupied.
//     - Recursively move to the next row.
//     - Backtrack by unmarking the column and diagonals.
// When all rows are filled, increment the solution count.

// Time Complexity: O(N!)
// Space Complexity: O(N)

class Solution {
    private int count = 0;

    public int totalNQueens(int n) {
        //Track the column and both types of diagonal
        boolean[] cols = new boolean[n];
        boolean[] d1 = new boolean[2 * n]; //Anti diagonal [/] (r + c)
        boolean[] d2 = new boolean[2 * n]; //Main diagonal [\] (r - c + n)

        backtrack(0, n, cols, d1, d2);
        return count;
    }

    private void backtrack(int row, int n, boolean[] cols, boolean[] d1, boolean[] d2) {
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {
            int id1 = row + col;
            int id2 = row - col + n;

            if (cols[col] || d1[id1] || d2[id2]) {
                continue;
            }

            //Place Queen 
            cols[col] = d1[id1] = d2[id2] = true;

            //Move to next row
            backtrack(row + 1, n, cols, d1, d2);

            //Remove Queen(Bactrack and reset)
            cols[col] = d1[id1] = d2[id2] = false;
        }
    }
}
