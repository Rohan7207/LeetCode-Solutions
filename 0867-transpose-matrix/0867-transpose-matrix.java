// Problem: Transpose Matrix
// Link: hhttps://leetcode.com/problems/transpose-matrix/
// Difficulty: Easy

// Approach:
// The transpose of a matrix is formed by swapping
// the row index and column index of every element.
// If:
//      matrix[i][j]
// is an element in the original matrix,
// then in the transpose it becomes:
//      transpose[j][i]
// Since the dimensions also get swapped:
//      Original  : m × n
//      Transpose : n × m
// Create a new matrix of size n × m.
// Traverse every cell of the original matrix.
// For every element:
//      transpose[j][i] = matrix[i][j]
// This automatically converts every row into a column
// and every column into a row.
// After processing all elements,
// return the transpose matrix.

// Time Complexity: O(m × n)
// Space Complexity: O(m × n)


class Solution {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] transpose = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }

        return transpose;
    }
}
