// Problem: Best Time to Buy and Sell Stock II
// Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
// Difficulty: Medium

// Approach:
// Traverse the prices array and capture every profit opportunity.
// If current day's price is greater than previous day's price:
//     - Add the difference to total profit.
// This works because multiple transactions are allowed,
// so every increasing sequence contributes to profit.
// Return the total accumulated profit.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }

        return profit;
    }
}
