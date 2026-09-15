class Solution {
    public int maxPalindromes(String s, int k) {
        // O(n ^ 2) and O(n)
        int n = s.length();
        int[] dp = new int[n + 1];

        // Precompute Palindrome
        boolean[][] pal = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) &&
                        (j - i < 2 || pal[i + 1][j - 1])) {
                    pal[i][j] = true;
                }
            }
        }

        for(int len = 0; len < k; len++) {
            dp[len] = 0;
        }

        for(int len = k; len <= n; len++) {
            // int res = solve(s, k, len - 1)
            int res = dp[len - 1];
            int j = len - 1;

            for(int i = 0; j - i + 1 >= k; i++) {
                if(pal[i][j]) {
                    res = Math.max(res, 1 + dp[i]);
                }
            }

            dp[len] = res;
        }

        // return solve(s, k, n);
        return dp[n];
    }
}

/*
    Approaches:
    1. Recursion + Memo => O(n ^ 3)
    2. Bottom-up => O(n ^ 3)
    3. Recursion + Memo + Precompute Palindrome => O(n ^ 2)
    4. Bottom-up + Precompute Palindrome => O(n ^ 2)
    5. solve(n) + Memo + Precompute Palindrome => O(n ^ 2), it is optimized bcz it uses only 1 state
    4. Bottom-up of solve(n) + Precompute Palindrome => O(n ^ 2)
*/

/*
    Solution-1: Got TLE with O(n ^ 2) and O(n ^ 2) bcz of recursion
    private int n;
    private int[][] dp;
    private boolean[][] pal;

    public int maxPalindromes(String s, int k) {
        // Overall => O(n ^ 3) and space O(n ^ 2)
        n = s.length();
        dp = new int[n + 1][n + 1]; //There O(n ^ 2) states and we call isPalindrome each time so O(n^3)

        if (k == 1) {
            return n; // I want max palindrome
        }

        // Precompute Palindrome bcz O(n ^ 3) => O(n ^ 2) we get better T.C
        pal = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) &&
                        (j - i < 2 || pal[i + 1][j - 1])) {
                    pal[i][j] = true;
                }
            }
        }

        return solve(s, k, 0, k - 1);
    }

    private int solve(String s, int k, int i, int j) {
        if (i >= n || j >= n) {
            return 0;
        }

        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        if (pal[i][j]) {
            int take = 1 + solve(s, k, j + 1, j + k);
            int grow = solve(s, k, i, j + 1);
            int slide = solve(s, k, i + 1, j + 1);

            return dp[i][j] = Math.max(take, Math.max(grow, slide));
        } else {
            int grow = solve(s, k, i, j + 1);
            int slide = solve(s, k, i + 1, j + 1);

            return dp[i][j] = Math.max(grow, slide);
        }
    }
*/

/*
    Bottom - up + Precompute Palindrome => O(n ^ 2) and O( n ^ 2)
     public int maxPalindromes(String s, int k) {
        // Overall => O(n ^ 2) and space O(n ^ 2)
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1]; //There O(n ^ 2) states and we call isPalindrome each time so O(n^3), we can change this to O(n ^ 2)  by precomputing palindromics information.

        if (k == 1) {
            return n; // I want max palindrome
        }

        // Precompute Palindrome bcz O(n ^ 3) => O(n ^ 2) we get better T.C
        boolean[][] pal = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) &&
                        (j - i < 2 || pal[i + 1][j - 1])) {
                    pal[i][j] = true;
                }
            }
        }

        // O(n ^ 2) and O(n ^ 2)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (pal[i][j]) {
                    // we need future ans so reverse loops
                    int take = 1 + (j + k <= n ? dp[j + 1][j + k] : 0);
                    int grow = dp[i][j + 1];
                    int slide = dp[i + 1][j + 1];

                    dp[i][j] = Math.max(take, Math.max(grow, slide));
                } else {
                    int grow = dp[i][j + 1];
                    int slide = dp[i + 1][j + 1];

                    dp[i][j] = Math.max(grow, slide);
                }
            }
        }

        // return solve(s, k, 0, k - 1);
        return dp[0][k - 1];
    }
*/

/*
    Improved code with O(n) states and O(n ^ 2) and O(n)
    private boolean[][] pal;
    private int[] dp;

    public int maxPalindromes(String s, int k) {
        int n = s.length();
        dp = new int[n + 1];

        // Precompute Palindrome
        pal = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) &&
                        (j - i < 2 || pal[i + 1][j - 1])) {
                    pal[i][j] = true;
                }
            }
        }

        return solve(s, k, n);
    }

    private int solve(String s, int k, int len) {
        if(len < k) {
            return 0;
        }

        if(dp[len] != 0) {
            return dp[len];
        }

        int res = solve(s, k, len - 1);

        int j = len - 1;
        for(int i = 0; j - i + 1 >= k; i++) {
            if(pal[i][j]) {
                res = Math.max(res, 1 + solve(s, k, i));
            }
        }

        return dp[len] = res;
    }
*/

/*
    Consider example:
        s = "aaaba"

    Now we need a max no.of substring of length atleast(>=) k and substring must be palindromic

    Now suppose we choose "aa" as substring which is palindromic and move to next substring which should be non-overlapping and "aba" is valid palindrome substring. now suppose we had taken "aaa" as substring which is also valid palindrome substring but we don't get "aba" substring bcz we need non-overlapping so intitution is start from i = 0 and j = k - 1,
        solve(s, k, 0, k - 1);
        s = given string, k = substring length should be greater than k
        0 = i pointer and k - 1 = j pointer and k - 1 because we need atleast k size and we can get >= k substring

        Now if substring is valid palindrome then wehave these options:
            - We can take that palindrome and move to next substring by moving i to j + 1 and j to j + k so we get valid length of k so call is, solve(s, k, j + 1, j + k);
            - We could grow the substring bcz >= k is allowed to make bigger substring bcz we need to try out every option so then call is, solve(s, k, i, j + 1);
            - We can also slide both pointer to try next "aa" so we slide both pointer forward. then call is, solve(s, k, i + 1, j + 1); 
            - We can't do solve(s, k, i + 1, j) bcz in that case length will become less than k

            And we return that max of three options max(take, grow, slide);
        If substring was not palindrome then there we have two option:
            - We can grow, solve(s, k, i, j + 1);
            - We can also slide, solve(s, k, i + 1, j + 1)

            And we return max of grow and slide

            ex: s = "xaaba" when i = 0 and j = 1 then it is not palindrome in this case the best option is slide where we get palindrome "aa"

        s = "aaaba"
        int solve(s, k, i, j) {
            if(i >= n || j >= n) {
                return 0;
            }

            if(isPalindrome(s, i, j)) { -> if s is palindrome from i to j then
                take = 1 + solve(s, k, j + 1, j + k);
                grow = solve(s, k, i, j + 1);
                slide = solve(s, k, i + 1, j + 1);

                return max(take, grow, slide);
            } else {   => if s = "xaaba"
                grow = solve(s, k, i, j + 1);
                slide = solve(s, k, i + 1, j + 1);

                return max(grow, slide);
            }
        }

        Memoization: dp[n + 1][n + 1];
*/

/*
    Another simple approach:

        - In previous approach we always we needed two pointers i and j
        - We could solve it by passing only length of string instead of solve(i, j)
         we pass only solve(n) and in each call we reduce
         In these case we can set j = n - 1 and can loop over string and try out every substring
         and check whether i to j is >= k or not.

        int solve(n) {
            

            // j - i + 1 >= k bcz len of substring must be greater than or equal to k
            // We pass i as n bcz we j is set at end we will get last substring palindrome first, and since we don't need overlapping substring we must try out the left part which is i only
            for ex = "abcaaabba" suppose j = 9 and i = 6 then between s[i..j] is palindrome so we pass left side to recursion which is 0 to i, its length is i only, 
            - since it is loop so when we get abba valid palindrome we call recursive call for left part and we also are trying next possibilities of not taking i by incrementing i and whaterver is max we store it in res.
            - And there is possibility that we could skip j also so we call res = solve(n - 1). And whaterver is max we store in res.

            if(n < k) {
                return 0;
            }

            int res = 0;
            int j = n - 1;
            for(int i = 0; j - i + 1 >= k; i++) {  
                if(pal[i][j]) {
                    res = max(res, 1 + solve(i));
                }
            }

            return res;
        }

        Memoization: dp[i];  // There n states with O(n) and there is i-loop so we do it in O(n ^ 2)
*/