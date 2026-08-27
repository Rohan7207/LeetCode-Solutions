class Solution {
    public int oddCells(int m, int n, int[][] indices) {
        int[][] matrix = new int[m][n];

        for (int[] indice : indices) {
            int row = indice[0];
            int col = indice[1];

            // Particular row
            for (int i = 0; i < n; i++) {
                matrix[row][i] += 1;
            }

            // Particular col
            for (int j = 0; j < m; j++) {
                matrix[j][col] += 1;
            }
        }

        int oddCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] % 2 == 1) {
                    oddCount++;
                }
            }
        }

        return oddCount;
    }
}