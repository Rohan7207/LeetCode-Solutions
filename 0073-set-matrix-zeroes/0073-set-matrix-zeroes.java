// Problem: Set Matrix Zeroes
// Link: https://leetcode.com/problems/set-matrix-zeroes/
// Difficulty: Medium

// Approach:
// Use the first row and first column as markers
// to avoid using extra space.
// Traverse the matrix:
//     - If a cell is 0, mark its row and column
//       by setting matrix[i][0] and matrix[0][j] to 0.
// Track whether the first column should become 0
// using a separate boolean variable.
// Traverse the matrix again starting from (1,1):
//     - If the corresponding row or column marker is 0,
//       set the current cell to 0.
// Finally, update the first row and first column
// if they were marked initially.

// Time Complexity: O(m * n)
// Space Complexity: O(1)


class Solution {
    public void setZeroes(int[][] matrix) {
        boolean firstCol = false;
        int r = matrix.length;
        int c = matrix[0].length;

        //Mark the position of 0 in first row and col 
        for (int i = 0; i < r; i++) {
            //First col is checked above
            if (matrix[i][0] == 0) {
                firstCol = true;
            }
            for (int j = 1; j < c; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        //Iterate over matrix if it is 0 change row and col as 0
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        //Since above starts with 1 and 1 we should check 1 position (0,0) and first col
        if (matrix[0][0] == 0) {
            for (int j = 0; j < c; j++) {
                matrix[0][j] = 0;
            }
        }

        //First col
        if (firstCol) {
            for (int i = 0; i < r; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
