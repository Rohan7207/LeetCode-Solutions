// Problem: Rotate Image
// Link: https://leetcode.com/problems/rotate-image/
// Difficulty: Medium

// Approach:
// Rotate the matrix layer by layer in clockwise direction.
// Traverse only the required half of the matrix.
// For each cell, perform a 4-way swap between:
//     - top
//     - left
//     - bottom
//     - right
// Store one value temporarily and rotate the remaining
// three values into their new positions.
// Repeat this process for every layer until the matrix is rotated.

// Time Complexity: O(n²)
// Space Complexity: O(1)


class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for(int i = 0; i < (n + 1) / 2; i++){
            for(int j = 0; j < n / 2; j++){
                int temp = matrix[n - 1 - j][i];

                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }
}
