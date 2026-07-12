// Problem: Stone Game
// Link: https://leetcode.com/problems/stone-game/
// Difficulty: Medium

// Approach:
// Instead of tracking Alice's and Bob's scores separately,
// store the maximum score difference the current player can
// achieve over the opponent.
// Define:
//     dp[left][right]
//     = Maximum score difference (Current Player - Opponent)
//       obtainable from piles[left...right].
// Base Case:
//     If only one pile remains,
//     the current player takes it.
//     dp[i][i] = piles[i]
// Transition:
//     From piles[left...right], the current player has two choices:
//     1. Take the left pile:
//            Gain = piles[left]
//            Remaining game = [left+1...right]
//            The opponent now becomes the current player,
//            whose advantage is dp[left+1][right].
//            Therefore,
//            takeLeft = piles[left] - dp[left+1][right]
//     2. Take the right pile:
//            Gain = piles[right]
//            Remaining game = [left...right-1]
//            Opponent's advantage = dp[left][right-1]
//            takeRight = piles[right] - dp[left][right-1]
//     Since the current player plays optimally,
//     choose the better option:
//     dp[left][right] = max(takeLeft, takeRight)
// DP Filling Order:
//     Since dp[left][right] depends on
//     dp[left+1][right] and dp[left][right-1],
//     fill the table by increasing interval length.
//     Length = 1  -> Base Case
//     Length = 2
//     Length = 3
//     ...
//     Length = n
// Final Answer:
//     dp[0][n-1] represents Alice's maximum score difference
//     over Bob for the entire array.
//     If dp[0][n-1] > 0,
//     Alice collects more stones and wins.

// Time Complexity:
//     DP Table : O(n²)
//
// Space Complexity:
//     DP Table : O(n²)


class Solution {
    public boolean stoneGame(int[] piles) {
        // Maximum score difference the current player can achieve from piles[left...right].
        int n = piles.length;
        int[][] dp = new int[n][n];

        // Fill the base case left == right
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        // Decide traversal order, Start with intervals of length 2.
        for (int len = 2; len <= n; len++) {
            // Find left and right
            for (int left = 0; left <= n - len; left++) {
                int right = left + len - 1;

                // Fill recurrence
                int takeLeft = piles[left] - dp[left + 1][right];
                int takeRight = piles[right] - dp[left][right - 1];

                dp[left][right] = Math.max(takeLeft, takeRight);
            }
        }

        return dp[0][n - 1] > 0;
    }
}
