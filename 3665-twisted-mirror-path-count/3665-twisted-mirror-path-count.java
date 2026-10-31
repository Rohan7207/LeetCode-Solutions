class Solution {

    private int m, n;
    private int MOD = 1000000007;
    private int left = 0; // Arrived at current cell from left cell(it means it was moving right)
    private int up = 1; // Arrived at current cell from up cell(it means it was moving down)

    public int uniquePaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        int[][][] memo = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return solve(grid, 0, 0, left, memo);
    }

    // Memoization = [i][j][2] => 2 bcz it can only two values left or up and initially -1
    private int solve(int[][] grid, int i, int j, int cameFrom, int[][][] memo) {
        if (i >= m || j >= n) {
            return 0;
        }

        if (i == m - 1 && j == n - 1) {
            return 1; // Found 1 way to reach last cell
        }

        if (memo[i][j][cameFrom] != -1) {
            return memo[i][j][cameFrom];
        }

        // Mirror cell
        if (grid[i][j] == 1) {
            if (cameFrom == left) {
                return memo[i][j][cameFrom] = solve(grid, i + 1, j, up, memo);
            } else {
                return memo[i][j][cameFrom] = solve(grid, i, j + 1, left, memo);
            }
        }

        // Normal cell
        int totalWays = 0;
        totalWays = (totalWays + solve(grid, i + 1, j, up, memo)) % MOD;
        totalWays = (totalWays + solve(grid, i, j + 1, left, memo)) % MOD;

        return memo[i][j][cameFrom] = totalWays;
    }
}

/*
    - There are two scenarios of mirror reflection if element enters path from left in mirror then it deflects down the matrix, and if path enters mirror from up the mirror deflects robot in right direction.

    - This is similar to DP in Grids approach where we need to find all valid paths from 0 to last cell. but there is one extra concept of mirror in problem.

    Note: If a reflection moves the robot into a mirror cell, the robot is immediately reflected again based on the direction it used to enter that mirror: if it entered while moving right, it will be turned down; if it entered while moving down, it will be turned right. This process will continue until either the last cell is reached, the robot moves out of bounds or the robot moves to a non-mirror cell.

        Means there are two mirrors consecutively, then deflection would work also on it like, means it works on recursion.
                R
                |
                1 -> 1
                     |
                     1 -> R   

    ** The main idea or trick to solve this, is to know how did we entered mirror cell from left in mirror or up in mirror, bcz based on only the problem asks do reflection rule.
        UP Cell =>  Right
        Left Cell => Down

    Thought Process: In DP on grids problems writing Bottom-up is easy but lets also write recursion, memo and bottom-up
        Ex:   0  1  0
              0  0  1
              1  0  0 

    - Generally we will take same grid size dp matrix. and at each cell we will store what are all possible path ways through which we can reach particular cell. To reach a current cell according to problem there are only two ways either from up and left cell.
            dp[i][j] = Total ways to reach (i, j) => we will add total ways to reach up and left cell,
                 dp[i][j] => dp[i - 1][j] + dp[i][j - 1];  // This works for bottom-up only not recursion.

     - Now consider if (i, j) cell is a mirror cell, then we need one more info like whether we entered from up or from left cell, so we would need pass another info to dp array i.e. i, j, cameFrom which stores from left cell or top cell.
     - We can demonstrate above scenario by keeping two variables Left = 0, Up = 1, and
        if cameFrom = 0, then it is came from left cell so mirror should deflect to down cell, i + 1, j
        if cameFrom = 1, then it is came from up cell so mirror should deflect to right cell, i, j + 1

    
   The above formula of dp on grids only works on bottom-up lets see, how to do with Recursion?
    - Consider a basic cell with grid[i][j] = 0, then we can call basic recurion to go right and down, with entry info.
        total = 0;
        // This below two steps help to pass the entry info to future next mirror cell so that it can decide where to go.
        total += solve(grid, i, j + 1, left);  // where left = 0 indicates that it came from left cell.
        total += solve(grid, i + 1, j, up);  // where up = 1 indicates that it came from up cell.

    - If cell is mirror, grid[i][j] = 1, then
        // We know the entry info then,
        if(grid[i][j] == 1) {
            if(cameFrom == left) {
                return solve(grid, i + 1, j, up);  // Pass up because tell next cell that came from up 
            } else {
              return solve(grid, i, j + 1, left);//Pass left because tell next cell that came from left
            }
        }
     
    Code of simple Recursion:
        solve(grid, i, j, cameFrom) {
            // Base case:
            if(i >= m || j >= n) return 0;

            if(i == m - 1 && j == n - 1) {
                return 1; // Found one way to reach des
            }

            // Case 1:
            total += solve(grid, i, j + 1, left);
            total += solve(grid, i + 1, j, up);

            // Case 2:
            if(grid[i][j] == 1) {
                if(cameFrom == left) {
                    return solve(grid, i + 1, j, up);  
                } else {
                    return solve(grid, i, j + 1, left);
                }
            }
        }

    - Lets see how to call a recursion function from main:
      - Since it is guranted that start and last cell are not mirror, we should start from 0, 0 and also for cameFrom value, but since it is start of matrix it does'nt matter what we pass left or up we have written logic in function, so
        solve(grid, 0, 0, left)  or solve(grid, 0, 0, up);
*/