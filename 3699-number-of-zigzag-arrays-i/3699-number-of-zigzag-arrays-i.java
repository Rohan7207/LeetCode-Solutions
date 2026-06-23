// Problem: Number of ZigZag Arrays I
// Link: https://leetcode.com/problems/number-of-zigzag-arrays-i/?envType=daily-question&envId=2026-06-23
// Difficulty: Hard

// Approach:
// We need to count arrays of length n where:
//     1. Every value is between l and r
//     2. Adjacent values are not equal
//     3. Directions must alternate
// The third condition means we cannot have:
//     a < b < c   // strictly increasing
//     a > b > c   // strictly decreasing
// So if the last move was UP, the next move must be DOWN.
// If the last move was DOWN, the next move must be UP.
// We use DP based on:
//     last value
//     last direction
// Let:
//     up[i] = number of valid arrays ending at value index i
//             where the last move was UP
//     down[i] = number of valid arrays ending at value index i
//               where the last move was DOWN
// Here value index i represents actual value:
//     l + i
// So total number of possible values is:
//     m = r - l + 1
// If n == 1:
//     Every single value from l to r is valid.
//     So answer is m.
// For length 2:
//     Any two different values are valid.
// If previous value is smaller than current value,
// then last move is UP.
// So:
//     up[i] = number of values smaller than i
//           = i
// If previous value is greater than current value,
// then last move is DOWN.
// So:
//     down[i] = number of values greater than i
//             = m - 1 - i
// Now build arrays from length 3 to n.
// To create newUp[i]:
//     The current value is i.
//     The previous value must be smaller than i.
//     Also, previous move must be DOWN.
// So:
//     newUp[i] = sum of down[j] for all j < i
// To create newDown[i]:
//     The current value is i.
//     The previous value must be greater than i.
//     Also, previous move must be UP.
// So:
//     newDown[i] = sum of up[j] for all j > i
// To calculate these sums efficiently:
//     use prefix sum for down[]
//     use suffix sum for up[]
// Prefix helps newUp because smaller values are on the left.
// Suffix helps newDown because greater values are on the right.
// After building length n,
// answer is:
//     sum(up) + sum(down)
// because a valid ZigZag array can end with either UP or DOWN.
// Return answer modulo 1e9 + 7.

// Time Complexity: O(n * m)
// Space Complexity: O(m)
//
// where:
//     m = r - l + 1


class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int MOD = 1000000007;

        int m = r - l + 1;

        if (n == 1)
            return m;

        long[] up = new long[m];
        long[] down = new long[m];

        for (int i = 0; i < m; i++) {
            up[i] = i;
            down[i] = m - 1 - i;
        }

        for (int len = 3; len <= n; len++) {
            long[] newUp = new long[m];
            long[] newDown = new long[m];

            long prefix = 0;
            for (int i = 0; i < m; i++) {
                newUp[i] = prefix;
                prefix = (prefix + down[i]) % MOD;
            }

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
