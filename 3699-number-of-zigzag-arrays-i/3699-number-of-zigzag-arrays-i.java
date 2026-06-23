class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int MOD = 1000000007;

        int m = r - l + 1;

        if (n == 1)
            return m;

        // up[i] = number of arrays ending at value index i where last move was UP
        long[] up = new long[m];
        // down[i] = number of arrays ending at value index i where last move was DOWN
        long[] down = new long[m];

        // initialize for length 2
        for (int i = 0; i < m; i++) {
            up[i] = i;
            down[i] = m - 1 - i;
        }

        // up = {0, 1, 2} down = {2, 1, 0}
        // build length 3 to n
        for (int len = 3; len <= n; len++) {
            long[] newUp = new long[m];
            long[] newDown = new long[m];

            // prefix of down 
            /*To create a new up ending at curr: previous value must be smaller than curr, previous direction must be down
            So: newUp[curr] = sum of down[prev] where prev < curr*/
            long prefix = 0;
            for (int i = 0; i < m; i++) {
                newUp[i] = prefix;
                prefix = (prefix + down[i]) % MOD;
            }

            // suffix of up
            /*To create a new down ending at curr: previous value must be greater than curr previous direction must be up
            So: newDown[curr] = sum of up[prev] where prev > curr */
            long suffix = 0;
            for (int i = m - 1; i >= 0; i--) {
                newDown[i] = suffix;
                suffix = (suffix + up[i]) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;
        for (int i = 0; i < m; i++) {
            ans = (ans + up[i] + down[i]) % MOD;
        }

        return (int) ans;
    }
}