class Solution {
    int[][] dp;
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(dp[i][j] == -1) {
                    ans = Math.max(ans, dfs(matrix, i, j));
                }
            }
        }

        return ans;
    }

    private int dfs(int[][] matrix, int i, int j) {
        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int num = matrix[i][j];

        int left = 0;
        int right = 0;
        int up = 0;
        int down = 0;

        if(i + 1 < m && matrix[i + 1][j] > num) {
            down = dfs(matrix, i + 1, j);
        }

        if(i - 1 >= 0 && matrix[i - 1][j] > num) {
            up = dfs(matrix, i - 1, j);
        }

        if(j + 1 < n && matrix[i][j + 1] > num) {
            right = dfs(matrix, i, j + 1);
        }

        if(j - 1 >= 0 && matrix[i][j - 1] > num) {
            left = dfs(matrix, i, j - 1);
        }

        dp[i][j] = 1 + Math.max(Math.max(up, down), Math.max(left, right));

        return dp[i][j];
    }
}

/*
     private int[][] dirs = {{0, 1}, {1, 0}, {0, -1},{-1, 0}};
    private int m, n;
        //Store the res of current cell in data structure we use dp
        //time = O(2*m*n)=O(m*n) space=O(m*n)

        if(matrix.length == 0) return 0;

        m = matrix.length;
        n = matrix[0].length;
        int[][] dp = new int[m][n];
        int ans = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                ans = Math.max(ans, dfs(matrix, i, j, dp));
            }
        }

        return ans;
    }

    Key Logic Breakdown
Memoization Check: if(dp[i][j] != 0) return dp[i][j];
This prevents redundant calculations. If the longest path starting from cell (i, j) has already been computed, it returns the stored value immediately.
Neighbor Exploration: The for loop iterates through adjacent cells (up, down, left, right).
Boundary & Condition Check:
x >= 0 && x < m && y >= 0 && y < n: Ensures the neighbor is within the matrix boundaries.
matrix[x][y] > matrix[i][j]: Ensures the path is strictly increasing.
Recursive Step: dp[i][j] = Math.max(dp[i][j], dfs(matrix, x, y, dp));
It updates the current cell's maximum path length based on the results from its neighbors.
Return Increment: return ++dp[i][j];
The ++ adds the current cell itself to the path length before returning. 
    private int dfs(int[][] matrix, int i, int j, int[][] dp){
        if(dp[i][j] != 0) return dp[i][j];  //if it is not visited then we check for it

        for(int[] d : dirs){
            int x = i + d[0], y = j + d[1];
            if(x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j])
                dp[i][j] = Math.max(dp[i][j], dfs(matrix, x, y, dp));
        }

        In the context of the Longest Increasing Path in a Matrix algorithm, the expression ++dp[i][j] performs two critical functions simultaneously: including the current cell in the path and updating the memoization table.
1. Counting the Current Cell
The longest path starting from cell (i, j) is defined as 1 (the cell itself) plus the longest path found in any of its neighbors that have a strictly greater value. 
Before this line, dp[i][j] holds the maximum path length returned by its neighbors.
The ++ (pre-increment) adds the current cell to that total, effectively calculating 1 + max_neighbor_path. 
2. Updating and Returning for Memoization
The ++dp[i][j] syntax is a concise way to handle the memoization table in Java:
Update: It increments the value stored in the dp[i][j] array index.
Return: It returns the newly incremented value to the previous recursive call in the stack.
Future Use: Once stored, any future calls to dfs(i, j) will hit the if(dp[i][j] != 0) check and return this value immediately, avoiding redundant calculations. 
        return ++dp[i][j];
    }
*/