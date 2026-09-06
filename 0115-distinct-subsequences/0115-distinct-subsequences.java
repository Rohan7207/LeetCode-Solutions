class Solution {
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
*/  