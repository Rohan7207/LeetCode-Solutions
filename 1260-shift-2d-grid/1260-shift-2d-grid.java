// Problem: Shift 2D Grid
// Link: https://leetcode.com/problems/shift-2d-grid/?envType=daily-question&envId=2026-07-20
// Difficulty: Easy

// Approach:
// Instead of performing k individual shifts, treat the 2D grid as a
// single 1D circular array.
// Let:
// • total = m × n
// Since shifting by 'total' positions brings every element back to its
// original position, first reduce unnecessary rotations:
//      k = k % total
// Traverse every cell in the grid:
// • Convert the current 2D position (i, j) into its equivalent
//   1D index.
// • Compute the new position after shifting:
//      newIndex = (index + k) % total
// • Convert the new 1D index back into its corresponding
//   2D position (newRow, newCol).
// • Place the current element into its new position in the
//   answer grid.
// After all elements are placed, convert the resulting 2D array
// into a List<List<Integer>> and return it.

// Time Complexity: O(m × n)
// Space Complexity: O(m × n)


class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        // Total number of elements
        int total = m * n;

        // Reduce unnecessary rotations
        k %= total;

        // Create a new grid to store shifted elements
        int[][] ans = new int[m][n];

        // Traverse every element
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Convert (i, j) to 1D index
                int index = i * n + j;

                // Find the new shifted index
                int newIndex = (index + k) % total;

                // Convert new 1D index back to (row, col)
                int newRow = newIndex / n;
                int newCol = newIndex % n;

                // Place current element in its new position
                ans[newRow][newCol] = grid[i][j];
            }
        }

        // Convert int[][] to List<List<Integer>>
        List<List<Integer>> list = new ArrayList<>();

        for (int[] row : ans) {
            List<Integer> innerList = new ArrayList<>();
            for (int val : row) {
                innerList.add(val);
            }

            list.add(innerList);
        }

        return list;
    }
}
