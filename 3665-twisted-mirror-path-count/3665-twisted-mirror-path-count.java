class Solution {

    private int MOD = 1000000007;
    private int left = 0; // Arrived at current cell from left cell(it means it was moving right)
    private int up = 1; // Arrived at current cell from up cell(it means it was moving down)

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // dp[row][col][left] = ways to reach this cell by entering from the left
        // dp[row][col][up] = ways to reach this cell by entering from the up
        int[][][] dp = new int[m][n][2];

        // Start cell : one way to be here (start is never a mirror, slot is arbitary)
        dp[0][0][left] = 1;

        // Prefill 0th row: one way to reach each cell, until first mirror
        for (int col = 1; col < n; col++) {
            dp[0][col][left] = 1;

            if (grid[0][col] == 1) {
                break; // Deflected down, cells beyond stay 0
            }
        }

        // Prefill 0th col: one way to reach each cell, until first mirror
        for (int row = 1; row < m; row++) {
            dp[row][0][up] = 1;

            if (grid[row][0] == 1) {
                break; // Deflected right, cells beyond stay 0
            }
        }

        // Fill the rest
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                // Entering from the left neighbour (row, col - 1)
                if (grid[row][col - 1] == 1) {
                    dp[row][col][left] = dp[row][col - 1][up]; // mirror: only UP-entrants turns right
                } else {
                    dp[row][col][left] = (dp[row][col - 1][up] + dp[row][col - 1][left]) % MOD;
                }

                // Entering from the up neighbour (row - 1, col)
                if (grid[row - 1][col] == 1) {
                    // mirror: only Left-entrants turns down
                    dp[row][col][up] = dp[row - 1][col][left];
                } else {
                    dp[row][col][up] = (dp[row - 1][col][up] + dp[row - 1][col][left]) % MOD;
                }
            }
        }

        // Destination is always empty: every arrivale counts, from either side
        return (dp[m - 1][n - 1][left] + dp[m - 1][n - 1][up]) % MOD;
    }
}

/*

    Recursion with memo : // T.C = O(m * n) and S.C = O(m * n)
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
*/

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


    Bottom appraoch by using recursion:
    - Make same memo array as used in recursion.
        dp[][] = {x, y}
        dp[i][j][left] = x  // Total no.of ways if came from left
        dp[i][j][up] = y  // Total no.of ways if came from up

            0            1    2   3

        0   {left, up}  {  }  {  }
        1
        2
        3

        For start cell, dp[0][0] = {  1 , 1 }
                                    Left, Up

                        dp[0][0][left] = 1;  // Bcz to reach from 0,0 to 0,0 is 1
                        dp[0][0][up] = 1;  // Bcz to reach from 0,0 to 0,0 is 1

    Tricky Part To understand:

        for 0th row since we can only travel in one direction right, right
          (0, 0) -> (0, 1) -> (0, 2) -> (0, 3)

        - Now consider that (0, 1) is mirror cell then it is imposible to go in nexxt cells bcz it deflects to down direction and since we cannot reach (0, 2) and (0, 3) from up so this cells are unreacable, means if in 0th row if any cell is mirror then we cannot go in its next right cells within row and this is true for 0th col if any cell is mirror then we cannot go in its down cells.

        // Base cases:
        // 0th Row fill
        for(int col = 1; col < n; col++) {
            // Since we can come only from left
            dp[0][col][left] = 1;

            // Can't go next right cells if mirror is there
            if(grid[0][col] == 1) {
                break;
            }
        }

        // 0th Col fill
        for(int row = 1; row < m; row++) {
            // Since we can come only from up
            dp[row][0][up] = 1;

            // Can't go next down cells if mirror is there
            if(grid[row][0] == 1) {
                break;
            }
        }

    // To fill remaining cells we can either use same recursion logic  or use the standard dp on grid logic, i.e.   dp[i][j] = dp[i - 1][j] + dp[i][j - 1];  lets use both techniques

    // Fill remaining cells
    for(row = 1; row < m; row++) {
        for(col = 1; col < n; col++) {
            // Lets see left cell i, j - 1 which can have two possibilites that is either 0 or 1
            // If it is 1, then it is confirm that we have came from up to left cell bcz it is mirror and since it deflects to right then it is fixed that we had came from up in left cell. And if we had came from left cell from left cell which one i.e. i, j - 2 then previous cell i, j - 1 would deflect it to down not right of curr cell.


            // No.of ways from left cell
            if(grid[row][col - 1] == 1) {
                // Total no.of path to reach row, col if i come from left then it would ask what is total no.of ways that left cell row, col - 1 from up row - 1, col - 1 since we can only come from above in left cell,
                dp[row][col][left] = dp[row][col - 1][up];  // Bcz if we came from up in row, col - 1 then only i can reach current cell
            } else {  // If value is 0
                // From left cell the no.of ways to reach row, col is no.of ways to reach from up and also left bcz the cell is not mirror
                dp[row][col][left] = dp[row][col - 1][up] + dp[row][col - 1][left];
            }

            // No.of ways from up cell
            if(grid[row - 1][col] == 1) {
                // Total no.of path to reach row, col if i come from up then it would ask what is total no.of ways that up cell row - 1, col from left row - 1, col - 1 since we can only come from left in up cell,
                dp[row][col][up] = dp[row - 1][col][left];  // Bcz if we came from left in row - 1, col  then only i can reach current cell
            } else {  // If value is 0
                // From up cell the no.of ways to reach row, col is no.of ways to reach from up and also left bcz the cell is not mirror
                dp[row][col][up] = dp[row - 1][col][up] + dp[row - 1][col][left];
            }
        }
    }

    [m - 1][n - 1] = {left, up}

    return (dp[m - 1][n - 1][left] + dp[m - 1][n - 1][up]) % MOD;

    We can solve Bottom-up:

    * DP on grids
    * Rec + Memo to bottom - up
*/