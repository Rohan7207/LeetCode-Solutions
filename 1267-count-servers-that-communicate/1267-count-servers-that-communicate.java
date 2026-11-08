// Problem: Count Servers that Communicate
// Link: https://leetcode.com/problems/count-servers-that-communicate/?envType=daily-question&envId=2026-11-08
// Difficulty: Medium

// Approach:
// 1. Create two arrays:
//      rowCount[i] -> number of servers in row i.
//      colCount[j] -> number of servers in column j.
//
// 2. Traverse the entire grid.
//      If grid[i][j] == 1:
//          Increment rowCount[i].
//          Increment colCount[j].
//
// 3. Traverse the grid again.
//      For every cell containing a server:
//          Check whether rowCount[i] > 1 OR colCount[j] > 1.
//
// 4. If either condition is true, the server can communicate.
//      Increment count.
//
// 5. Return count.

// Time Complexity: O(m * n)
// Space Complexity: O(m + n)


class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] rowCount = new int[m];
        int[] colCount = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && (rowCount[i] > 1 || colCount[j] > 1)) {
                    count++;
                }
            }
        }

        return count;
    }
}
