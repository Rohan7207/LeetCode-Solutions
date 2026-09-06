class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[][] dp = new int[m + 1][n + 1];

        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Base 1
        // if(n == 0) return 1;   // Found one sequence
        // dp[m][0] = 1;
        for(int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        // Base 2
        // if(m == 0) return 0;   
        // dp[0][n] = 0;
        for(int j = 1; j <= n; j++) {
            dp[0][j] = 0;
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(s.charAt(i - 1) == t.charAt(j - 1)) {
                    // dp[m][n] = solve(s, t, dp, m - 1, n - 1) + solve(s, t, dp, m - 1, n);
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    // dp[m][n] = solve(s, t, dp, m - 1, n);
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // return solve(s, t, dp, m, n);
        return dp[m][n];
    }
}

/*
    // First Recursion type:
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[][] dp = new int[m + 1][n + 1];

        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solve(s, t, dp, 0, 0);
    }

    private int solve(String s, String t, int[][] dp, int i, int j) {
        if(j == t.length()) {
            return 1;   // Found one sequence
        }

        if(i == s.length()) {
            return 0;   
        }

        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        if(s.charAt(i) == t.charAt(j)) {
            dp[i][j] = solve(s, t, dp, i + 1, j + 1) + solve(s, t, dp, i + 1, j);
        } else {
            dp[i][j] = solve(s, t, dp, i + 1, j);
        }

        return dp[i][j];
    }
*/

/*
    // Second Recusrion type by which we can easily convert to bottom up
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[][] dp = new int[m + 1][n + 1];

        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solve(s, t, dp, m, n);
    }

    private int solve(String s, String t, int[][] dp, int m, int n) {
        if(n == 0) {
            return 1;   // Found one sequence
        }

        if(m == 0) {
            return 0;   
        }

        if(dp[m][n] != -1) {
            return dp[m][n];
        }

        if(s.charAt(m - 1) == t.charAt(n - 1)) {
            dp[m][n] = solve(s, t, dp, m - 1, n - 1) + solve(s, t, dp, m - 1, n);
        } else {
            dp[m][n] = solve(s, t, dp, m - 1, n);
        }

        return dp[m][n];
    }
*/

/*
    s = rabbbit  t = rabbit
        i            j      -> match
         i            j     -> match
          i            j    -> match, now we can either choose this and explore or check next state 
                                i + 1  

    // Recursion:
    rough idea: solve(s, t, dp, 0, 0) where dp[m][n]
                            i, j

        if(j == t.length) return 1;  found 1 matching sequence 

        if(i == s.length) return 0;  Exhausted search return 0 saying not found sequence

        // Memoization
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        
        if(s[i] == t[i]) {
           dp[i][j] = solve(s, t, i + 1, j + 1) + solve(s, t, i + 1, j);
        } else{
           dp[i][j] = solve(s, t, i + 1, j)
        }
    if both charcters match we have two choices either move both i and j and explore or else move  
        only i and explore future

    Second Recursion type where we use m and n and when we convert recursion to bottom =up it becomes easy to convert just m to i and n to j:

    solve(s, t, m, n): With memoization,
    if(n == 0) return 1;

    if(m == 0) return 0;

    if(s.charAt(m - 1) == t.charAt(n - 1)) {
        solve(s, t, m - 1, n - 1) + solve(s, t, m - 1, n);
    } else {
        solve(s, t, m - 1, n);
    }

    Convertion of second type to bottom up approach: dp[m][n] = x; x is count of subsequence of s == t

    // if(n == 0) return 1:
        for(i = 0; i <= n; i++) {      dp = 1 0 0 0 0
            dp[i][0] = 1;                   0
        }                                   0
                                            0
    // if(m == 0) return 0;                 0
        for(j = 1; j <= m; j++) {
            dp[0][j] = 0;
        }

    // if(s.charAt(m - 1) == t.charAt(n - 1)) {
    //     solve(s, t, m - 1, n - 1) + solve(s, t, m - 1, n);
    // } else {
    //     solve(s, t, m - 1, n);
    // }:

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

    // solve(s, t, m, n) : function call,
     return dp[m][n];
*/  