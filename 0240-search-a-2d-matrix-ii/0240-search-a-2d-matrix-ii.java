// Problem: Search a 2D Matrix
// Link: https://leetcode.com/problems/search-a-2d-matrix-ii/
// Difficulty: Medium

// Approach:
// Start from the top-right corner of the matrix.
// For each position:
//     - If current element equals target,
//       return true.
//     - If current element is smaller than target,
//       move down because all elements on the left
//       are smaller and cannot contain target.
//     - If current element is greater than target,
//       move left because all elements below
//       are greater and cannot contain target.
// Continue until indices go out of bounds.
// If target is not found, return false.

// Time Complexity: O(m + n)
// Space Complexity: O(1)


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int r = 0, c = n - 1;
        
        while (r < m && c >= 0) {
            if (target == matrix[r][c]) {
                return true;
            } else if (matrix[r][c] < target) {
                r++;
            } else {
                c--;
            }
        }
        return false;
    }
}
