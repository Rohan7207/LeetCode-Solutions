// Problem: Maximum Number of Items From Sale II
// Link: https://leetcode.com/problems/maximum-number-of-items-from-sale-ii/?envType=daily-question&envId=2026-11-28
// Difficulty: Medium

// Approach:
//
// 1. Count the frequency of every factor using freq[].
//    Multiple items can have the same factor.
//
// 2. For every factor f, count how many items have factors
//    divisible by f.
//
//    gain[f] = count - 1
//
//    Subtract 1 because the purchased item itself cannot
//    be received as a free item.
//
// 3. Find minPrice, the cheapest item price.
//
// 4. Sort all items in ascending order of price.
//
// 5. A purchase of item i can provide:
//       - 2 copies if it is a boosted purchase.
//       - 1 copy if it is a normal purchase.
//
//    A boosted purchase costs price[i] and gives 2 copies.
//    A normal purchase costs minPrice and gives 1 copy.
//
// 6. A boosted purchase is worth considering only when:
//
//       price[i] <= 2 * minPrice
//
//    Otherwise, spending the same money on the cheapest item
//    gives at least as many copies.
//
// 7. Process items in ascending price order.
//    For each item, purchase as many boosted copies as possible,
//    limited by gain[factor] and the remaining budget.
//
// 8. Spend the remaining budget on the cheapest item.

// Time Complexity: O(n log n)
// Space Complexity: O(n)


class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        // Step 1: Count occurrences of each factor
        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Step 2: Calculate the number of eligible free item types
        int[] gain = new int[n + 1];

        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0)
                continue;

            int count = 0;

            // Count items whose factors are multiples of f
            for (int j = f; j <= n; j += f) {
                count += freq[j];
            }

            // Exclude the item itself
            gain[f] = count - 1;
        }

        // Step 3: Sort items by increasing price
        int[][] sorted = items.clone();

        Arrays.sort(sorted, (a, b) -> Integer.compare(a[1], b[1]));

        long ans = 0;

        // Step 4: Purchase useful boosted copies first
        for (int[] item : sorted) {
            int factor = item[0];
            int price = item[1];

            // Boosted purchase is not better than normal purchase
            if (price > 2L * minPrice) {
                break;
            }

            int available = gain[factor];

            // Maximum boosted purchases possible
            int take = (int) Math.min(
                    (long) available,
                    budget / price);

            // Each boosted purchase gives 2 copies
            ans += 2L * take;

            budget -= take * price;
        }

        // Step 5: Spend remaining budget on cheapest item
        ans += budget / minPrice;

        return (int) ans;
    }
}
