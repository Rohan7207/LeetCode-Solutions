// Problem: Stone Game III
// Link: https://leetcode.com/problems/stone-game-iii/?envType=daily-question&envId=2026-08-03
// Difficulty: Hard

// Approach:
// Let dp[i] represent the maximum score difference
// (current player − opponent) that the current player can
// achieve starting from index i.
//
// Instead of tracking Alice's and Bob's scores separately,
// store only the score difference.
//
// Traverse the array from right to left because dp[i]
// depends on future states:
//
//      dp[i + 1]
//      dp[i + 2]
//      dp[i + 3]
//
// For every index i, the current player has three choices:
//
// 1. Take one stone.
// 2. Take two stones.
// 3. Take three stones.
//
// Maintain a running sum (take) to avoid recomputing the
// total value of the chosen stones.
//
// If the current player takes 'take' points, the opponent
// will then play optimally starting from the next index,
// achieving a score difference of dp[next].
//
// Therefore, the current player's score difference becomes:
//
//      take - dp[next]
//
// Compute this for all valid choices (1, 2, or 3 stones)
// and store the maximum value in dp[i].
//
// After filling the DP table:
//
// - dp[0] > 0  → Alice has a higher score.
// - dp[0] < 0  → Bob has a higher score.
// - dp[0] == 0 → Both finish with the same score.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1]; 

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;

            int take = 0;

            for (int k = 0; k < 3 && k + i < n; k++) {
                take += stoneValue[i + k];

                dp[i] = Math.max(dp[i], take - dp[i + k + 1]);
            }
        }

        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        }

        return "Tie";
    }
}
