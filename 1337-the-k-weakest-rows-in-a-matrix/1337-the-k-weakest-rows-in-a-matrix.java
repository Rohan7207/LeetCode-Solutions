// Problem: The K Weakest Rows in a Matrix
// Link: https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
// Difficulty: Easy

// Approach:
// Use Sorting + Store Row Index with Soldier Count.
//
// 1. Count the number of soldiers (1s) in every row.
//
// 2. Store both the soldier count and the original row index
//    together as [count, index].
//
// 3. Sort the rows using two conditions:
//      - Fewer soldiers → weaker row.
//      - If soldier counts are equal, smaller row index → weaker row.
//
// 4. After sorting, take the indices of the first k rows.
//
// 5. Return these indices as the answer.

// Time Complexity: O(m × n + m log m)
// Space Complexity: O(m)


class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        Integer[][] rows = new Integer[m][2];

        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    count++;
                }
            }

            rows[i][0] = count; // count of soldiers
            rows[i][1] = i; // row index
        }

        Arrays.sort(rows, (a, b) -> {
            if (!a[0].equals(b[0])) {
                return a[0] - b[0]; // fewer soldiers first
            }

            return a[1] - b[1]; // smaller index first
        });

        int[] res = new int[k];

        for (int i = 0; i < k; i++) {
            res[i] = rows[i][1];
        }

        return res;
    }
}
