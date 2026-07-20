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