// Problem: Climbing Stairs II
// Link: https://leetcode.com/problems/climbing-stairs-ii/?envType=daily-question&envId=2026-11-10
// Difficulty: Medium

// Approach:
// 1. Create dp[n + 1], where dp[i] represents the minimum
//    cost required to reach the top (stair n) starting from stair i.
//
// 2. Start from stair n - 1 and move backwards to stair 0.
//
// 3. From each stair i, we have three possible jumps:
//      a. Jump to i + 1.
//      b. Jump to i + 2.
//      c. Jump to i + 3.
//
// 4. For every valid jump to stair j, calculate:
//      costs[j - 1] + (j - i) * (j - i) + dp[j]
//
// 5. Take the minimum among all valid jumps.
//
// 6. Store the minimum in dp[i].
//
// 7. dp[n] = 0 because reaching the top requires no additional cost.
//
// 8. Return dp[0].

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int cost = Integer.MAX_VALUE;

            int j = i + 1;
            if (j <= n) { 
                cost = costs[j - 1] + (j - i) * (j - i) + dp[j];
            }

            j = i + 2;
            if (j <= n) {
                cost = Math.min(cost, costs[j - 1] + (j - i) * (j - i) + dp[j]);
            }

            j = i + 3;
            if (j <= n) { 
                cost = Math.min(cost, costs[j - 1] + (j - i) * (j - i) + dp[j]);
            }

            dp[i] = cost;
        }

        return dp[0];
    }
}
