// Problem: Matrix Diagonal Sum
// Link: https://leetcode.com/problems/matrix-diagonal-sum/
// Difficulty: Easy

// Approach:
// 1. Get the size of the square matrix n.
//
// 2. Traverse the matrix using a single loop.
//
// 3. Primary diagonal:
//    Row index = i, Column index = i
//    Element = mat[i][i]
//
// 4. Secondary diagonal:
//    Row index = i, Column index = n - 1 - i
//    Element = mat[i][n - 1 - i]
//
// 5. Add both diagonal elements to the sum.
//
// 6. If n is odd, both diagonals share the center element.
//    Subtract it once because it was counted twice.
//
// 7. Return the final sum.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += mat[i][i];
            sum += mat[i][n - 1 - i];
        }

        if (n % 2 == 1) {
            sum -= mat[n / 2][n / 2];
        }

        return sum;
    }
}
