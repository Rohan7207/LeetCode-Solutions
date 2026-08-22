// Problem: Check Divisibility by Digit Sum and Product
// Link: https://leetcode.com/problems/container-with-most-water/
// Difficulty: Easy

// Approach:
// Extract each digit of n using modulo 10.
//
// 1. Store the original number because n will be reduced to 0.
//
// 2. Use:
//      n % 10 → gets the last digit
//      n / 10 → removes the last digit
//
// 3. Maintain:
//      sum     → sum of all digits
//      product → product of all digits
//
// 4. Calculate:
//      total = sum + product
//
// 5. Check whether the original number is divisible by total.

// Time Complexity: O(log10(n))
// Space Complexity: O(1)


class Solution {
    public boolean checkDivisibility(int n) {
        int original = n;
        int sum = 0;
        int product = 1;

        while (n > 0) {
            int digit = n % 10;
            sum += digit;
            product *= digit;
            n /= 10;
        }

        int total = sum + product;

        return original % total == 0;
    }
}
