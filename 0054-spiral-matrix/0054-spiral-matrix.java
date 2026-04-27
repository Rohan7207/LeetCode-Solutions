class Solution {
    public List<Integer> spiralmatrix(int[][] matrix, List<Integer> res, int r, int c) {
        int i;
        int k = 0, l = 0; //k=row l=col
        while (k < r && l < c) {
            //Left to Right
            for (i = l; i < c; i++) {
                res.add(matrix[k][i]);
            }
            k++;
            //Top to Bottom
            for (i = k; i < r; i++) {
                res.add(matrix[i][c - 1]);
            }
            c--;
            if (k < r) {
                //Right to Left
                for (i = c - 1; i >= l; i--) {
                    res.add(matrix[r - 1][i]);
                }
                r--;
            }
            if (l < c) {
                //Bottom to Top
                for (i = r - 1; i >= k; i--) {
                    res.add(matrix[i][l]);
                }
                l++;
            }
        }
        return res;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int r = matrix.length;
        int c = matrix[0].length;
        spiralmatrix(matrix, res, r, c);

        return res;
    }
}