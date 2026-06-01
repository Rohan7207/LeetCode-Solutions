// Problem: Minimum Cost of Buying Candies With Discount
// Link: https://leetcode.com/problems/minimum-cost-of-buying-candies-with-discount/?envType=daily-question&envId=2026-06-01
// Difficulty: Easy

// Approach:
// Sort the candies in ascending order.
// Traverse from the largest candy
// towards the smallest.
// Every third candy is taken for free.
// Use:
//     (cost.length - 1 - i)
// to determine the position from the
// largest candy.
// Add only the candies that are not
// in a free position.

// Time Complexity: O(n log n)
// Space Complexity: O(1)


class Solution {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);

        int min = 0;
       
        for(int i = cost.length - 1; i >= 0; i--) {
            if((cost.length - 1 - i) % 3 != 2) {
                min += cost[i];
            }
        }

        return min;
    }
}
