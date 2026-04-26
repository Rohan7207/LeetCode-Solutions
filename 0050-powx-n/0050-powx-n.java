// Problem: Pow(x, n)
// Link: https://leetcode.com/problems/powx-n/
// Difficulty: Medium

// Approach:
// Use recursion with binary exponentiation to calculate powers efficiently.
// Base case:
//     - If exponent becomes 0, return 1.
// Recursively calculate x^(n/2).
//     - If n is even, return half * half.
//     - If n is odd, return half * half * x.
// For negative exponents:
//     - Convert x to 1/x
//     - Convert exponent to positive.
// Use long datatype to handle overflow when n = Integer.MIN_VALUE.

// Time Complexity: O(log n)
// Space Complexity: O(log n)


class Solution {
    public double help(double x, long n) {
        if (n == 0)
            return 1;
        double half = help(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    public double myPow(double x, int n) {
        long N = n;
        if (n < 0) {
            x = 1 / x;
            N = -N;
        }
        return help(x, N);
    }
}
