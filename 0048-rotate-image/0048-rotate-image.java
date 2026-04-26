class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for(int i = 0; i < (n + 1) / 2; i++){
            for(int j = 0; j < n / 2; j++){
                int temp = matrix[n - 1 - j][i];

                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }
}

        //1st approach:
        //We convert matrix into transpose and then reverse the row elemnts
        //of transpose matrix with O(2(m*n) = O(m*n) and O(1)

        /*
        2nd approach = O(m*n) and O(1)

        when n = 2 we rotate 1 element to right position
        when n=3 we rotate 2 elements to right position for inner element i.e. single
        element is kept as it is
        when n=4 we rotate 3 elements to right for inner layer there are 4 elements
        we apply n=2 and rotate to 1 position
        
        */
