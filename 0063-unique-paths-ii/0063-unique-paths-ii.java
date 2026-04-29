class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        if (obstacleGrid == null || obstacleGrid[0][0] == 1) {
            return 0;
        }

        int[] dp = new int[cols];
        dp[0] = 1; 

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (obstacleGrid[r][c] == 1) {
                    dp[c] = 0; 
                } else {
                    if (c > 0) { 
                        dp[c] += dp[c - 1];
                    }
                }
            }
        }

        return dp[cols - 1];
    }
}

//time = O(r * c) and space = O(c)

/*
    Set dp[0] to 1, representing the number of ways to reach the starting cell (0, 0).
    
    If there is obstacle set it 0, indicating that there is no path to reach that cell
    
    It should not be zero since we are adding the left value to it
*/