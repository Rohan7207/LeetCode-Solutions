// Problem: Predict the Winner
// Link: https://leetcode.com/problems/predict-the-winner/?envType=daily-question&envId=2026-08-01
// Difficulty: Medium

// Approach:
// If the array length is even, Player 1 can always guarantee a win (or tie)
// by choosing either all even-indexed or all odd-indexed elements,
// whichever has the larger total. Hence, return true immediately.
//
// For an odd-length array, use Dynamic Programming.
//
// Instead of storing the individual scores of both players,
// store the maximum score difference:
//
//      currentPlayerScore - opponentScore
//
// Let dp[j] represent the maximum score difference that the current
// player can obtain for the subarray nums[i...j].
//
// Initialization:
// Copy nums into dp because when the subarray contains only one element,
// the current player simply picks that element, so the score difference
// equals that value.
//
// Transition:
// For every subarray nums[i...j], the current player has two choices:
//
// 1. Pick the left element:
//      nums[i] - dp[j]
//
//    After picking nums[i], the opponent plays optimally on
//    nums[i+1...j], whose best score difference is dp[j].
//
// 2. Pick the right element:
//      nums[j] - dp[j-1]
//
//    After picking nums[j], the opponent plays optimally on
//    nums[i...j-1], whose best score difference is dp[j-1].
//
// Choose the better option:
//
//      dp[j] = max(nums[i] - dp[j],
//                  nums[j] - dp[j-1])
//
// The loops iterate backwards so that the required subproblems are
// already computed before updating dp[j].
//
// Finally, dp[n-1] represents the maximum score difference for the
// entire array.
//
// If dp[n-1] >= 0, Player 1 can guarantee at least a tie.

// Time Complexity: O(n²)
// Space Complexity: O(n)


class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;

        if (n % 2 == 0) return true;

        int[] dp = nums.clone();
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }

        return dp[n - 1] >= 0;
    }
}
