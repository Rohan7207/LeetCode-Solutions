// Problem: Stone Game V
// Link: https://leetcode.com/problems/stone-game-v/?envType=daily-question&envId=2026-08-17
// Difficulty: Hard

// Approach:
// Use Dynamic Programming + Prefix Sum.
//
// The game works on a subarray [left, right]. Alice can split this
// subarray at every possible position i into:
//
//     [left ... i] | [i+1 ... right]
//
// 1. Use prefixSum to calculate the sum of any subarray in O(1).
//
// 2. Let solve(left, right) represent the maximum score Alice can
//    obtain from the subarray [left, right].
//
// 3. For every possible partition:
//
//    - If leftSum < rightSum:
//      Alice can keep only the left part and gains leftSum.
//
//    - If leftSum > rightSum:
//      Alice can keep only the right part and gains rightSum.
//
//    - If leftSum == rightSum:
//      Alice can choose either side, so take the better option.
//
// 4. Add the selected side's sum to the best score obtainable from
//    that remaining subarray.
//
// 5. Store the result in memo[left][right] so the same subproblem
//    is not solved again.
//
// Base case:
// When left == right, only one stone remains, so no more score can
// be obtained.

// Time Complexity: O(n³)
// Space Complexity: O(n²)


class Solution {

    int[][] memo;
    int[] prefixSum;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        memo = new int[n][n];
        prefixSum = new int[n + 1];

        // Compute prefix sums for O(1) subarray sum queries
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + stoneValue[i];
        }

        return solve(0, n - 1);
    }

    private int solve(int left, int right) {
        // Base case: only one stone left, game ends (no further score)
        if (left == right) {
            return 0;
        }

        // Return memoized result if already calculated
        if (memo[left][right] != 0) {
            return memo[left][right];
        }

        int maxScore = 0;

        // Try every partition point k
        for (int i = left; i < right; i++) {
            int leftSum = prefixSum[i + 1] - prefixSum[left];
            int rightSum = prefixSum[right + 1] - prefixSum[i + 1];

            if (leftSum < rightSum) {
                maxScore = Math.max(maxScore, leftSum + solve(left, i));
            } else if (leftSum > rightSum) {
                maxScore = Math.max(maxScore, rightSum + solve(i + 1, right));
            } else {
                // When sums are equal, Alice chooses the side that yields maximum total score
                maxScore = Math.max(maxScore, leftSum + Math.max(solve(left, i), solve(i + 1, right)));
            }
        }

        memo[left][right] = maxScore;
        return maxScore;
    }
}
    }
*/
