class Solution {
    public void setZeroes(int[][] matrix) {
        boolean firstCol = false;
        int r = matrix.length;
        int c = matrix[0].length;

        //Mark the position of 0 in first row and col 
        for (int i = 0; i < r; i++) {
            if (matrix[i][0] == 0) {
                firstCol = true;
            }
            //First col is checked above
            for (int j = 1; j < c; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        //Iterate over matrix if it is 0 change row and col as 0
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        //Since above starts with 1 and 1 we should check 1 position (0,0) and first col
        if (matrix[0][0] == 0) {
            for (int j = 0; j < c; j++) {
                matrix[0][j] = 0;
            }
        }

        //First col
        if (firstCol) {
            for (int i = 0; i < r; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}

//Better approach with space=O(m+n)
        /*We create hashset of rows and cols and add position of 0 present in
        matrix and we visit hashsets and at 0's we convert rows and columns to 0
            time= O(n) and space=O(m+n)
        */
        //Optimal approach with space=O(1) and time = O(m*n)
        /* As hashset we do not create extra sets but we treat the first row and
        col to store the position of 0 and create space for (0,0) because it conflicts with row and col and if we find 0 we mark in its row and column
        and then we change the row and col to 0's*/