class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Initialize a 2D array of BitSet objects
        BitSet[][] dp = new BitSet[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new BitSet(1024); // Pre-allocates space for 1024 bits
            }
        }

        // Base case initialization
        dp[0][0].set(grid[0][0]);

        for (int i = 0; i < m; i++) { // O(m)
            for (int j = 0; j < n; j++) { // O(n)
                if (i == 0 && j == 0) {
                    continue;
                }

                // Initialize 'combined' as a single BitSet object instead of up and left
                BitSet combined = new BitSet(1024);

                // Read from the top cell if it exists
                if (i > 0) {
                    combined.or(dp[i - 1][j]);
                }

                // Read from the left cell if it exists
                if (j > 0) {
                    combined.or(dp[i][j - 1]);
                }

                // Initialize a new empty BitSet to hold the final state of dp[i][j]
                BitSet nextState = new BitSet(1024);

                // Optimized loop: only iterates over active bits instead of all 1024
                for (int xorVal = combined.nextSetBit(0); xorVal >= 0; xorVal = combined.nextSetBit(xorVal + 1)) {
                    int newXorVal = xorVal ^ grid[i][j];
                    nextState.set(newXorVal);
                }

                // Save the transitioned state into the DP table
                dp[i][j] = nextState;
            }
        }

        // Find the smallest valid XOR value in the destination cell
        for (int xorVal = 0; xorVal < 1024; xorVal++) { // O(1)
            if (dp[m - 1][n - 1].get(xorVal)) {
                return xorVal;
            }
        }

        return -1; // O(m * n) and O(m * n)
    }
}

/*
    // Simple approach with boolean array
    class Solution {
    public int minCost(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        boolean[][][] dp=new boolean[m][n][1024];
        dp[0][0][grid[0][0]]=true;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0&&j==0){
                    continue;
                }if(i>0){
                    for(int x=0;x<1024;x++){
                        if(dp[i-1][j][x]){
                            dp[i][j][x^grid[i][j]]=true;
                        }
                    }
                }if(j>0){
                    for(int x=0;x<1024;x++){
                        if(dp[i][j-1][x]){
                            dp[i][j][x^grid[i][j]]=true;
                        }
                    }
                }
            }
        }for(int x=0;x<1024;x++){
            if(dp[m-1][n-1][x]){
                return x;
            }
        }return -1;
    }
}
*/

/* 
    1 2 
    3 4

    6 7
    5 8

    1 2 3 
    4 5 6

     1 ^ 2 = 3 and 1 ^ 4 = 5 choose 3
     3 ^ 3 = 0  and 3 ^ 5 = 6  if we choose 0 as min
      0 ^ 6 = 6 but if we had taken 6 then 6 ^ 6 = 0, so we can't make local choices. So we can't use greedy method.

    - This problem is similar to problems like to find max or min path sum from start and end cell, 
        * In this type problem, we would create a same grid and fill values according to upper value and left cell value and based on problem we would take max or min, so in general we store the maximum value to reach that particular cell from top(0, 0)
         * It is dp on grids:
         - generic = dp[i][j] = grid[i][j] + max(grid[i - 1][j], grid[i][j - 1]);

    ** Now lets see will using DP on Grids works in this problem also, lets illustrate with ex:

    Ex: _ _ _ _ _
        _ _ 2 _ _
        _ 3 1 _ _
        _ _ _ _ _

     - lets suppose we have came through one path and we got one cell value as 2 and in other we got 3 now we are trying find min xor value at cell 1, so if we decide on going that min element gives better ans then it would be 2 ^ 1 = 3 but the better ans is 3 ^ 1 = 2, so we can just decide based on min and max value of above and left values.
     - We need to also see that we can reach one cell through any path so there can be any xor values present in it or there can be multiple xor values based on which we have taken, so that means we need to find min of every possible xor values present in that particular cell and find min,so there dp matrix can store multiple values based on paths, so then we would need to find xor for each values and store multiple xor values in current cell also, like



                {2, 3, 5...}
    {2, 4, 6..} {a, b, c...}

    where a is current cell xor 2, and similary full array and same for left, so what does a, b, c... represents so if we had followed one particular and got 2 and we follow same path and get a as new xor value for that path at current position, and it occurs for all values and paths. and one more thing we would not need to store duplicates values in one particular state cell bcz in end we only need what possible xor values that particular cell contains and based on that we derive xor values for current cell.

    So we are storing the set in matrix:
        List<List<Set<Integer>>>;
    we can also use simple data structure instead of set, lets see it in next but lets see what the costraints are given bcz it contain some unique values are given:

    **Constraints:
        1 <= m == grid.length <= 1000
        1 <= n == grid[i].length <= 1000
        m * n <= 1000
        0 <= grid[i][j] <= 1023​

    - Check that grid values are upto 1023 only, we can say that there are no xor values which can cross 1023.
    - One thing notice about xor:
        7 ->  0 1 1 1
        9 ->  1 0 0 1
              1 1 1 0  = 14 but xor operation never increases no.of bits if it is 4 then we get 4

        1023 -> 0 0 1 1 1 1 1 1 1 1 1 1
         1   ->                       1
                0 0 1 1 1 1 1 1 1 1 1 0    => 1022 (so there is no extra bit so res will only consists 10 bits)    
     
    ***- So we can say that, whatever we get the values of Xor, it would be of max to max of 10 bit values bcz 1023 contains 10 bits. we need this bcz since we are storing the xor values for each cell, now wkt the max value of xor would not cross 1023, so it can be any values btw 0...1023, so instead of taking a proper a set we can take a boolean array of size 1024.

    ***--  So the dp we would represent it as
            int[][][] dp = new int[m][n][1024];

        -- We can also reduce boolean array by using the bitset(1024), bitset provides the effective operation to work on bits.

        // 
            
        //     does java contains bitset

        //     AI Mode
        //     All
        //     Videos
        //     Forums
        //     Shopping
        //     Images
        //     Short videos
        //     More
        //     Tools
        //     AI Overview
        //     Yes, Java contains a BitSet class. It is located in the standard utility library under the java.util.BitSet package. 

        //     Oracle Help Center
        //     +1
        //     Instead of using a standard array of booleans (which wastes space because each boolean usually takes up a full byte of memory), BitSet implements a vector of bits that grows automatically as needed. Each component holds a binary value (0 or 1, representing false or true). 

        //     edureka.co
        //     +1
        //     Common Operations
        //     The class provides a variety of built-in methods to manipulate individual bits or perform bulk bitwise operations:
        //     set(int index): Turns a bit "on" (true) at the specified position. 

        //     YouTube
        //     ·FTC Team 7477 - Super 7
        //     +1
        //     clear(int index): Turns a bit "off" (false). 

        //     Oracle Help Center
        //     +1
        //     get(int index): Inspects the value of a bit, returning a boolean. 

        //     Oracle Help Center
        //     +1
        //     Bitwise algebra: You can perform logical operations between two different BitSet objects, such as and(), or(), and xor()
        // 

    - So core principle of this problem is, if we are at any cell and what possible xor values we would take all possible values of above and left cell and do the xor operation with current cell to get all possible xor values at particular cell.
    - When we reach at last cell, it also represents all possible xor values from all possible paths, so we would take minimum xor values and return it.
    - So in each cell we have stored all possible xor values from all possible paths and it looks like it takes to much space but it doesn't take due to constraint of 1023 and also it works efficiently.


    Ex: If bit is present at that index then that would be set to 1 otherwise to 0,
                       0   1  2   3   4  ... 500..  1023
(top) bitset1[1024] =  0   1  1   0   0       1      1

                       0   1  2   3   4  ... 500..  1023
(left)bitset2[1024] =  0   0  1   1   0       1      0

(combined) up | left=  0   1  1   1   0       1      1   (represents all possible xor values from both bitsets)  

    curr cell values => To remove xor of above to bit sets we would perform | operation on both bitsets
    left | up, which represents a actual all possible xor values that we needed,
    - Next when we find up | left which represents all xor values of up and left we would iterate in this and do xor will index since values are stored as indexes.
    - Bitsets is used bcz it does the work up | left very efficiently.

    for(val = 0; val < 1024; val++) {
        if(combined[val]) {
            int xorVal = grid[i][j] ^ val;
            dp[i][j][xorVal] = 1;   // We are setting bitset values as 1 for future cell calculation
        }
    }


    Final code:

        for(i = 0; i < m; i++) {
            for(j = 0; j < n; j++) {
                bitset<1024> up = dp[i - 1][j];
                bitset<1024> left = dp[i][j - 1];

                bitset<1024> combined = up | left;
                for(val = 0; val < 1024; val++) {
                    if(combined[val]) {
                        int xorVal = grid[i][j] ^ val;
                        dp[i][j][xorVal] = 1;    
                    }
                }
            }
        }

                        0 1 2 3 ..  194  1023
    dp[m - 1][n - 1] -> 0 1 1 0 1   1       1

    It represent that to reach a cell m - 1, n - 1 there exists path whose xor value is 2 if it is set 1, so since we need min we find first index which is set to 1 and return that index.


*/