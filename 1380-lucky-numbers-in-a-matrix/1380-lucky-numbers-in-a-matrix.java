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