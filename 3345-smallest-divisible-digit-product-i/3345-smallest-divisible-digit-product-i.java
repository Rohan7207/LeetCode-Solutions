// Problem: Smallest Divisible Digit Product I
// Link: https://leetcode.com/problems/smallest-divisible-digit-product-i/?envType=daily-question&envId=2026-08-06
// Difficulty: Easy

// Approach:
// Start from the given number n and keep checking consecutive
// integers until a valid one is found.
//
// For each number:
//
// 1. Compute the product of all its digits.
// 2. If the product becomes 0 during computation, stop early
//    since multiplying by any remaining digits will still keep
//    the product as 0.
// 3. Check whether the final product is divisible by t.
//
// If the product is divisible by t, return the current number.
//
// Otherwise, increment n and repeat the process until a valid
// number is found.

// Time Complexity:
// Let d be the number of digits and x be the number of integers
// checked before finding the answer.
//
// O(x × d)
//
// Space Complexity:
// O(1)


class Solution {
    public int smallestNumber(int n, int t) {
        while (!check(n, t)) {
            n++;
        }

        return n;
    }

    private boolean check(int num, int t) {
        int product = 1;

        while (num > 0) {
            product *= num % 10;
            num /= 10;

            if (product == 0) break;
        }

        return product % t == 0;
    }
}
