// Problem: Maximum Product of Two Digits
// Link: https://leetcode.com/problems/maximum-product-of-two-digits/?envType=daily-question&envId=2026-07-25
// Difficulty: Easy

// Approach:
// Traverse every digit of the number from right to left using modulo (%) and
// division (/).
// Maintain two variables:
// - first  -> largest digit seen so far.
// - second -> second largest digit seen so far.
// For each extracted digit:
// 1. If the digit is greater than the current largest digit,
//    shift the current largest digit to second,
//    and update first with the new digit.
// 2. Otherwise, if the digit is greater than the current second largest,
//    update second.
// After processing all digits, first and second contain the two largest
// digits in the number.
// Return the product of these two digits.

// Time Complexity: O(d), where d is the number of digits.
// Space Complexity: O(1)


class Solution {
    public int maxProduct(int n) {
        int first = 0;
        int second = 0;

        while (n > 0) {
            int digit = n % 10;

            if (digit > first) {
                second = first;
                first = digit;
            } else if (digit > second) {
                second = digit;
            }

            n /= 10;
        }

        return first * second;
    }
}
