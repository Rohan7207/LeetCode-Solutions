// Problem: Knight Dialer
// Link: https://leetcode.com/problems/knight-dialer/
// Difficulty: Medium

// Approach:
// The problem asks for the total number of distinct phone numbers of length n
// that can be formed using valid knight moves on a keypad.
//
// Think recursively:
// helper(digit, len)
// = Number of phone numbers of length 'len' starting from 'digit'.
//
// Step 1:
// Store all valid knight moves for each digit in an adjacency list.
//
// Example:
// 1 -> {6, 8}
// 4 -> {0, 3, 9}
// 5 -> {}
//
// Step 2:
// Define the recursive state:
//
// helper(digit, len)
//
// where:
// - digit = current position of the knight.
// - len   = remaining length of the phone number.
//
// Step 3:
// Base Case:
//
// If len == 1,
// only the current digit remains to be placed,
// so there is exactly one valid phone number.
// return 1;
//
// Step 4:
// For every valid knight move from the current digit,
// recursively count the number of phone numbers of length (len - 1).
//
// ways = Σ helper(nextDigit, len - 1)
//
// Step 5:
// Since the same state (digit, len) can be reached multiple times,
// store its answer in dp[digit][len] to avoid recomputation.
//
// Step 6:
// The knight may start from any digit (0 to 9),
// so compute:
//
// answer = helper(0, n)
//        + helper(1, n)
//        + ...
//        + helper(9, n)
//
// Take modulo 1e9 + 7 after every addition.

// Time Complexity:
// O(10 × n)
// (There are only 10 digits, and each state is computed once.)
//
// Space Complexity:
// O(10 × n)
// (Memoization table + recursion stack O(n))


class Solution {

    private static final int MOD = 1_000_000_007;

    // Knight moves from each digit
    int[][] moves = {
            { 4, 6 }, // 0
            { 6, 8 }, // 1
            { 7, 9 }, // 2
            { 4, 8 }, // 3
            { 0, 3, 9 }, // 4
            {}, // 5
            { 0, 1, 7 }, // 6
            { 2, 6 }, // 7
            { 1, 3 }, // 8
            { 2, 4 } // 9
    };

    public int knightDialer(int n) {
        Integer[][] dp = new Integer[10][n + 1];
        long ans = 0;

        // Try every digit as the starting digit
        for (int digit = 0; digit <= 9; digit++) {
            ans = (ans + helper(digit, n, dp)) % MOD;
        }

        return (int) ans;
    }

    private int helper(int digit, int len, Integer[][] dp) {
        // Base Case
        if (len == 1) {
            return 1;
        }

        // Memoization
        if (dp[digit][len] != null) {
            return dp[digit][len];
        }

        long ways = 0;

        // Try every valid knight move
        for (int move : moves[digit]) {
            ways = (ways + helper(move, len - 1, dp)) % MOD;
        }

        dp[digit][len] = (int) (ways % MOD);

        return dp[digit][len];
    }
}
