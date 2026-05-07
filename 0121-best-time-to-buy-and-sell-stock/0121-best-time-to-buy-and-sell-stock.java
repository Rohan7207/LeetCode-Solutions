// Problem: Best Time to Buy and Sell Stock
// Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
// Difficulty: Easy

// Approach:
// Traverse the prices array while tracking
// the minimum stock price seen so far.
// For each day:
//     - Update minimum price if current price is smaller.
//     - Calculate profit by selling on current day:
//           current price - minimum price
//     - Update maximum profit if current profit is larger.
// Return the maximum profit found.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int profit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }

            profit = Math.max(profit, prices[i] - min);
        }
        
        return profit;
    }
}
