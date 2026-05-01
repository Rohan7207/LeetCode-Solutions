// Problem: Search a 2D Matrix
// Link: https://leetcode.com/problems/search-a-2d-matrix/
// Difficulty: Medium

// Approach:
// Use binary search on rows to find the row
// that may contain the target.
// Check whether the target lies between
// the first and last element of the middle row.
//     - If yes, apply binary search on that row.
//     - If target is greater than the last element,
//       search in lower rows.
//     - Otherwise search in upper rows.
// In the selected row, use binary search
// to find the target element.
// Return true if found, otherwise false.

// Time Complexity: O(log m + log n)
// Space Complexity: O(1)


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int strow = 0;
        int endrow = m - 1;

        while (strow <= endrow) {
            int midrow = strow + (endrow - strow) / 2;
            if (target >= matrix[midrow][0] && target <= matrix[midrow][n - 1]) {
                //Found the row which has target
                return searchinrow(matrix, target, midrow);
            } else if (target >= matrix[midrow][n - 1]) {
                //down=right
                strow = midrow + 1;
            } else {
                endrow = midrow - 1;
            }
        }
        return false;
    }

    private boolean searchinrow(int[][] matrix, int target, int row) {
        int n = matrix[0].length;
        int st = 0;
        int end = n - 1;

        while (st <= end) {
            int mid = st + (end - st) / 2;
            if (target == matrix[row][mid]) {
                return true;
            } else if (target > matrix[row][mid]) {
                st = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }
}
