class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //time = O(r * c) and space = O(c)

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        if(obstacleGrid == null || obstacleGrid[0][0] == 1){
            return 0;
        }

        int[] dp = new int[cols];
        dp[0] = 1;   //Set dp[0] to 1, representing the number of ways to reach the starting cell (0, 0).

        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(obstacleGrid[r][c] == 1){
                    dp[c] = 0;     //if there is obstacle set it 0, indicating that there is no path to reach that cell
                }else{
                    if(c > 0){   //it should not be zero since we are adding the left value to it
                        dp[c] += dp[c - 1];
                    }
                }
            }
        }

        return dp[cols - 1];
    }
}