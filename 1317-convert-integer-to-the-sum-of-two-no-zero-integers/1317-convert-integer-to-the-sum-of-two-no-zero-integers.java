// Problem: Convert Integer to the Sum of Two No-Zero Integers
// Link: https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/
// Difficulty: Easy

// Approach:
// Use Brute Force + Digit Checking.
//
// 1. Try every possible value of `a` from 1 to n - 1.
// 2. Calculate the corresponding `b = n - a`.
// 3. Check whether both `a` and `b` contain no digit `0`.
// 4. If both are valid, return the pair.
// 5. If no pair is found, return an empty array.

// Time Complexity: O(n log n)
// Space Complexity: O(1) excluding the returned array.


class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int a = 1; a < n; a++) {
            int b = n - a;

            if (isNoZero(a) && isNoZero(b)) {
                return new int[] { a, b };
            }
        }

        return new int[] {};
    }

    private boolean isNoZero(int num) {
        while (num > 0) {
            if (num % 10 == 0) {
                return false;
            }

            num /= 10;
        }

        return true;
    }
}
