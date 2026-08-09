// Problem: Stone Game II 
// Link: https://leetcode.com/problems/stone-game-ii/?envType=daily-question&envId=2026-08-09
// Difficulty: Medium

// Approach:
// Use Dynamic Programming with memoization.
//
// Step 1: Build a suffix-sum array.
// `suffixSum[i]` represents the total number of stones
// from index `i` to the end.
// This allows us to quickly know how many stones remain
// from any position.
//
// Step 2: Define the DP state:
//
// maxStones(suffixSum, M, i)
// where:
// - `i` = index of the first remaining pile.
// - `M` = current value of M.
//
// From this state, the current player can take
// X piles where:
//
//     1 <= X <= 2 * M
//
// Step 3: Base case.
// If the current player can take all remaining piles,
// they simply take everything:
//
//     return suffixSum[i]
//
// Step 4: Try every possible choice of X.
//
// After taking X piles:
// - The game moves to `i + X`.
// - The new M becomes `max(M, X)`.
// - The opponent then plays optimally.
//
// Instead of directly calculating the current player's
// score, calculate the opponent's best possible score.
//
// `res` stores the minimum score that the opponent can
// leave for the current player by choosing the best move.
//
// Since the total remaining stones are `suffixSum[i]`:
//
//     current player's maximum score
//     = total remaining stones - opponent's minimum result
//
// Therefore:
//
//     dp[i][M] = suffixSum[i] - res
//
// Step 5: Memoization.
// Store the result for every `(i, M)` state so that the
// same game state is not solved repeatedly.

// Time Complexity:
// O(n^3) in this implementation.
//
// Space Complexity:
// O(n^2) for the memoization table and O(n) for suffix sums
// (excluding recursion stack).


class Solution {
    public int stoneGameII(int[] piles) {
        // Store the suffix sum of all array elements.
        int n = piles.length;
        int[] suffixSum = Arrays.copyOf(piles, n);

        for (int i = suffixSum.length - 2; i >= 0; i--) {
            suffixSum[i] += suffixSum[i + 1];
        }

        return maxStones(suffixSum, 1, 0, new int[n][n]);
    }

    private int maxStones(int[] suffixSum, int maxTillNow, int currIndex, int[][] memo) {
        // If currIndex + 2*maxTillNow lies outside the array, pick all remaining stones.
        if (currIndex + 2 * maxTillNow >= suffixSum.length) {
            return suffixSum[currIndex];
        }

        if (memo[currIndex][maxTillNow] > 0) {
            return memo[currIndex][maxTillNow];
        }

        int res = Integer.MAX_VALUE;

        // Find the minimum value res for the next move possible for opponent.
        for (int i = 1; i <= 2 * maxTillNow; i++) {
            res = Math.min(res, maxStones(suffixSum, Math.max(i, maxTillNow), currIndex + i, memo));
        }

        // Memoize the difference of suffixSum[p] and res. This denotes the maximum stones that can be picked.
        memo[currIndex][maxTillNow] = suffixSum[currIndex] - res;
        
        return memo[currIndex][maxTillNow];
    }
}
