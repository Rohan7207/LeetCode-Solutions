// Problem: Create Grid With Exactly One Path
// Link: https://leetcode.com/problems/create-grid-with-exactly-one-path/?envType=daily-question&envId=2026-10-27
// Difficulty: Easy

// Approach:
// 1. Create a character grid of size m × n.
// 2. Fill every row with '#' using Arrays.fill().
// 3. Fill the first row with '.' to create a horizontal path.
// 4. Fill the last column from row 1 onward with '.' to create
//    a vertical path connected to the first row.
// 5. Create a String array of size m.
// 6. Convert each character row into a String and store it in ans.
// 7. Return ans.

// Time Complexity: O(m * n)
// Space Complexity: O(m * n)


class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }

        for (int j = 0; j < n; j++) {
            grid[0][j] = '.';
        }

        for (int i = 1; i < m; i++) {
            grid[i][n - 1] = '.';
        }

        String[] ans = new String[m];

        for (int i = 0; i < m; i++) {
            ans[i] = new String(grid[i]);
        }

        return ans;
    }
}
