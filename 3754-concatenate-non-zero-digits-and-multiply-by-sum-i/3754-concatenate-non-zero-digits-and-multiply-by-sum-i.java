// Problem: Concatenate Non-Zero Digits and Multiply by Sum I
// Link: https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-i/?envType=daily-question&envId=2026-07-07
// Difficulty: Easy

// Approach:
// Traverse the number digit by digit from right to left.
// Maintain:
//     • res        -> Number formed using only non-zero digits.
//     • sum        -> Sum of all non-zero digits.
//     • placeValue -> Current decimal position (1, 10, 100...).
// For every extracted digit:
//     • If the digit is non-zero:
//          - Add it to the digit sum.
//          - Place it at the current position
//            in the reconstructed number.
//          - Move to the next decimal place.
//     • Ignore zero digits completely.
// Continue until all digits are processed.
// Finally,
// multiply the reconstructed number by the
// sum of its digits and return the result.

// Time Complexity: O(d)
// Space Complexity: O(1)
//
// where d is the number of digits.


class Solution {
    public long sumAndMultiply(int n) {
        int res = 0;
        int sum = 0;
        int placeValue = 1;

        while (n > 0) {
            int digit = n % 10;

            if (digit != 0) {
                sum += digit;
                res += digit * placeValue;
                placeValue *= 10;
            }

            n /= 10;
        }

        return (long) res * sum;
    }
}
