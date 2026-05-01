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