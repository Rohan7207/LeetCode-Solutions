// Problem: GCD of Odd and Even Sums
// Link: https://leetcode.com/problems/gcd-of-odd-and-even-sums/?envType=daily-question&envId=2026-07-15
// Difficulty: Easy

// Approach:
// Instead of generating the first n odd and even numbers,
// use the mathematical formulas for their sums.
// Sum of first n odd numbers:
//     1 + 3 + 5 + ... = n²
// Sum of first n even numbers:
//     2 + 4 + 6 + ... = n(n + 1)
// Compute both sums in O(1).
// Then find their Greatest Common Divisor (GCD)
// using Euclid's Algorithm.
// Euclid's Algorithm repeatedly replaces:
//     gcd(a, b)
// with
//     gcd(b, a % b)
// until b becomes 0.
// At that point, a is the required GCD.

// Time Complexity: O(log(min(oddSum, evenSum)))
// Space Complexity: O(1)


class Solution {
    public int gcdOfOddEvenSums(int n) {
        int oddSum = n * n;
        int evenSum = n * (n + 1);

        return gcd(oddSum, evenSum);
    }

    private int gcd(int a, int b) {
        while (b > 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}
