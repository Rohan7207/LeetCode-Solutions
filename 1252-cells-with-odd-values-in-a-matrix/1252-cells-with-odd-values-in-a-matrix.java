// Problem: Cells With Odd Values in a Matrix
// Link: https://leetcode.com/problems/cells-with-odd-values-in-a-matrix/
// Difficulty: Easy

// Approach:
// Use a matrix simulation.
//
// 1. Create an m x n matrix initialized with 0.
//
// 2. For every [row, col] in indices:
//
//      - Increment every cell in that row.
//      - Increment every cell in that column.
//
// 3. After processing all indices, traverse the entire matrix.
//
// 4. If matrix[i][j] is odd:
//
//      matrix[i][j] % 2 == 1
//
//    increment oddCount.
//
// 5. Return oddCount.

// Time Complexity:
// O(indices.length * (m + n) + m * n)
//
// Space Complexity:
// O(m * n)


class Solution {
    public int oddCells(int m, int n, int[][] indices) {
        int[][] matrix = new int[m][n];

        for (int[] indice : indices) {
            int row = indice[0];
            int col = indice[1];

            for (int i = 0; i < n; i++) {
                matrix[row][i] += 1;
            }

            for (int j = 0; j < m; j++) {
                matrix[j][col] += 1;
            }
        }

        int oddCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] % 2 == 1) {
                    oddCount++;
                }
            }
        }

        return oddCount;
    }
}
