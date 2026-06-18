// Problem: Min Cost Climbing Stairs
// Link: https://leetcode.com/problems/min-cost-climbing-stairs/
// Difficulty: Easy

// Approach:
// dp[i] = minimum cost needed to reach the top
// starting from stair i.
// From stair i:
//     First, we must pay cost[i].
// Then we can jump either:
//     1 step -> i + 1
//     2 steps -> i + 2
// We choose the cheaper future path.
// Therefore:
//     dp[i] = cost[i] + min(dp[i + 1], dp[i + 2])
// Base cases:
//     dp[n] = 0
//     dp[n + 1] = 0
// These represent reaching the top or crossing the top.
// No extra cost is needed from there.
// Fill dp from right to left because:
//     dp[i] depends on dp[i + 1] and dp[i + 2]
// Finally, we can start from step 0 or step 1,
// so return:
//     min(dp[0], dp[1])

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 2];
        dp[n] = 0;
        dp[n + 1] = 0;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = cost[i] + Math.min(dp[i + 1], dp[i + 2]);
        }

        return Math.min(dp[0], dp[1]);
    }
}
