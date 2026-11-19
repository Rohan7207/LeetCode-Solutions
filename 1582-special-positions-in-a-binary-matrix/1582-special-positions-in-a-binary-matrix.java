// Problem: Special Positions in a Binary Matrix
// Link: https://leetcode.com/problems/special-positions-in-a-binary-matrix/
// Difficulty: Easy

// Approach:
// 1. Create two arrays:
//    row[i] stores the number of 1s in row i.
//    col[j] stores the number of 1s in column j.
//
// 2. Traverse the matrix once.
//    Whenever mat[i][j] == 1:
//       Increment row[i].
//       Increment col[j].
//
// 3. Traverse the matrix again.
//
// 4. For every cell containing 1, check:
//    row[i] == 1 && col[j] == 1
//
// 5. If both conditions hold, increment the result.
//
// 6. Return the result.

// Time Complexity: O(m * n)
// Space Complexity: O(m + n)


class Solution {
    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] row = new int[m];
        int[] col = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1 && row[i] == 1 && col[j] == 1) {
                    res++;
                }
            }
        }

        return res;
    }
}
