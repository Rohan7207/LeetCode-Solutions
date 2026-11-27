// Problem: Maximum Number of Items From Sale I
// Link: https://leetcode.com/problems/maximum-number-of-items-from-sale-i/?envType=daily-question&envId=2026-11-27
// Difficulty: Medium

// Approach:
// 1. Precompute the free-copy benefit of each item.
//    copies[i] = number of other items whose factors are
//    divisible by items[i][0].
//
// 2. Find the minimum price among all item types.
//    This is used in the base case to purchase as many
//    additional copies as possible.
//
// 3. Define dp[i][b] as the maximum number of copies
//    obtainable using the first i item types with budget b.
//
// 4. Base case:
//    With zero item types selected, we can spend the budget
//    buying copies of the cheapest item.
//
// 5. For each item, consider two choices:
//
//    TAKE:
//    Buy one copy of this item type.
//    Gain 1 purchased copy + its free copies.
//    Remaining budget = b - price.
//
//    SKIP:
//    Do not purchase this item type.
//    Keep the previous result.
//
// 6. Take the maximum of both choices.

// Time Complexity: O(n² + n * budget)
// Space Complexity: O(n * budget)


class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[][] dp = new int[n + 1][budget + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int[] copies = new int[n]; // copies[i] = count of copies if I buy ith item

        int minPrice = 1501;
        for (int i = 0; i < n; i++) {
            minPrice = Math.min(minPrice, items[i][1]);

            int copy = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                if (items[j][0] % items[i][0] == 0) {
                    copy++;
                }
            }

            copies[i] = copy;
        }

        for (int b = 0; b <= budget; b++) {
            dp[0][b] = b / minPrice;
        }

        for (int i = 1; i <= n; i++) {
            for (int b = 0; b <= budget; b++) {
                int price = items[i - 1][1];
                int take = 0;

                if (price <= b) {
                    take = 1 + copies[i - 1] + dp[i - 1][b - price];
                }

                int skip = dp[i - 1][b];

                dp[i][b] = Math.max(take, skip);
            }
        }

        return dp[n][budget];
    }
}
