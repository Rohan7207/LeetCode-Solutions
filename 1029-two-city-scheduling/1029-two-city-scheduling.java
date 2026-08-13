// Problem: Two City Scheduling
// Link: https://leetcode.com/problems/two-city-scheduling/
// Difficulty: Medium

// Approach:
// Use a greedy strategy based on the relative cost of sending each
// person to City A versus City B.
//
// 1. For every person, calculate:
//
//       difference = costA - costB
//
//    This tells us how much cheaper/expensive City A is compared
//    to City B.
//
// 2. Sort all people by this difference in ascending order.
//
//    - Smaller difference → A is relatively better.
//    - Larger difference  → B is relatively better.
//
// 3. Since exactly n people must go to each city:
//    - Send the first n people to City A.
//    - Send the remaining n people to City B.
//
// 4. Add the corresponding costs to get the minimum total cost.

// Time Complexity: O(n log n)
//
// Space Complexity: O(log n)
// depending on the sorting implementation.


class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int n = costs.length / 2;

        // Sort by A - B, assign First n → B and Remaining n → A
        Arrays.sort(costs, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));

        int total = 0;
        for (int i = 0; i < costs.length; i++) {
            if (i < n) {
                total += costs[i][0]; // A
            } else {
                total += costs[i][1]; // B
            }
        }

        return total;
    }
}
