// Problem: Final Prices With a Special Discount in a Shop
// Link: https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop
// Difficulty: Easy

// Approach:
// 1. Create an array-based stack to store indices whose discounts are not yet found.
// 2. Traverse prices from left to right.
// 3. For each price[i], compare it with the price at the index on top of the stack.
// 4. While the stack is not empty and prices[st[top]] >= prices[i]:
//    - prices[i] is the first qualifying discount for the item at st[top].
//    - Subtract prices[i] from that item's price.
//    - Pop its index from the stack.
// 5. Push the current index i onto the stack because its discount is not yet known.
// 6. Return prices. Any indices left in the stack receive no discount.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] st = new int[n];
        int top = -1;

        for (int i = 0; i < n; i++) {
            while (top >= 0 && prices[st[top]] >= prices[i]) {
                prices[st[top--]] -= prices[i];
            }

            st[++top] = i;
        }

        return prices;
    }
}
