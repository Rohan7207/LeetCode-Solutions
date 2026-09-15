class Solution {
    public int maxPalindromes(String s, int k) {
        // Overall => O(n ^ 3) and space O(n ^ 2)
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1]; //There O(n ^ 2) states and we call isPalindrome each time so O(n^3)

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
        for(int i = n - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(pal[i][j]) {
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
}

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