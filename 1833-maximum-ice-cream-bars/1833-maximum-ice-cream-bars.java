// Problem: Maximum Ice Cream Bars
// Link: https://leetcode.com/problems/maximum-ice-cream-bars/?envType=daily-question&envId=2026-06-21
// Difficulty: Medium

// Approach:
// We need to buy the maximum number of ice cream bars using given coins.
// Since the boy can buy bars in any order, the best strategy is to always buy
// the cheapest bars first.
// Instead of sorting the costs array using Arrays.sort(), we use Counting Sort.
// We count how many ice cream bars exist for each cost.
// Example:
// costs = [1, 3, 2, 4, 1]
// freq[1] = 2
// freq[2] = 1
// freq[3] = 1
// freq[4] = 1
// Now we process costs from lowest to highest.
// This gives the same effect as sorting the array.
// For each cost:
//     canBuy = coins / cost
// This tells how many bars of this cost we can afford.
// But we cannot buy more bars than available.
// So:
//     buy = min(freq[cost], canBuy)
// Add buy to answer and reduce coins by:
//     buy * cost
// If coins become less than the current cost,
// then we cannot buy this cost or any higher cost,
// so we can stop.
// Finally return the total bars bought.

// Time Complexity: O(n + maxCost)
// Space Complexity: O(maxCost)


class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int maxCost = 0;

        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }

        int[] freq = new int[maxCost + 1];

        for (int cost : costs) {
            freq[cost]++;
        }

        int bars = 0;

        for (int cost = 1; cost <= maxCost; cost++) {
            if (freq[cost] == 0) continue;

            int canBuy = coins / cost;

            int buy = Math.min(freq[cost], canBuy);

            bars += buy;

            coins -= buy * cost;

            if(coins < cost) break;
        }

        return bars;
    }
}
