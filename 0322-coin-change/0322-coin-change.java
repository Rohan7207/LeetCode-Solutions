// Problem: Coin Change
// Link: https://leetcode.com/problems/coin-change/
// Difficulty: Easy

// Approach:
// Use Dynamic Programming where
// dp[i] represents the minimum number
// of coins needed to make amount i.
// Initialize all amounts as unreachable
// using amount + 1, except:
//     dp[0] = 0
// For every amount from 1 to target:
//     - Try every coin.
//     - If the coin can contribute to
//       the current amount,
//       update dp[i] using:
//       1 + dp[i - coin]
// Store the minimum value among all choices.
// After filling the DP array:
//     - If dp[amount] is still unreachable,
//       return -1.
//     - Otherwise return dp[amount].

// Time Complexity: O(amount × number_of_coins)
// Space Complexity: O(amount)


class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] amt = new int[amount + 1];
        Arrays.fill(amt, amount + 1);
        amt[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    amt[i] = Math.min(amt[i], 1 + amt[i - coins[j]]);
                }
            }
        }

        if (amt[amount] < amount + 1) {
            return amt[amount];
        }

        return -1;
    }
}
