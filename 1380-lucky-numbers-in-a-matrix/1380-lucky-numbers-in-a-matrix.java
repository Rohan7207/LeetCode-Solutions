// Problem: Lucky Numbers in a Matrix
// Link: https://leetcode.com/problems/lucky-numbers-in-a-matrix/
// Difficulty: Easy

// Approach:
// Use Row Minimum + Column Maximum.
//
// 1. Find the minimum element of every row and store it in minRowElements.
//
// 2. Find the maximum element of every column and store it in
//    maxColElements.
//
// 3. Traverse the matrix and check whether an element is both:
//    - Minimum in its row.
//    - Maximum in its column.
//
// 4. If both conditions hold, add the element to the answer.

// Time Complexity: O(m * n)
// Space Complexity: O(m + n)


class Solution {
    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;

        int[] minRowElements = new int[m];
        int[] maxColElements = new int[n];

        for (int i = 0; i < m; i++) {
            int min = matrix[i][0];

            for (int j = 1; j < n; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }

            minRowElements[i] = min;
        }

        for (int j = 0; j < n; j++) {
            int max = matrix[0][j];

            for (int i = 1; i < m; i++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }

            maxColElements[j] = max;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (minRowElements[i] == maxColElements[j]) {
                    ans.add(matrix[i][j]);
                }
            }
        }

        return ans;
    }
}
