// Problem: Second Largest Digit in a String
// Link: https://leetcode.com/problems/second-largest-digit-in-a-string/
// Difficulty: Easy

// Approach:
// Instead of tracking the two largest digits explicitly, iterate over the
// possible digit characters from '9' down to '0'.
// For each digit:
// 1. Check whether it exists in the string using indexOf().
// 2. If it exists, increment the count of distinct digits found.
// 3. The first digit found is the largest digit.
// 4. The second distinct digit found is the second largest digit,
//    so return its numeric value.
// If the loop finishes without finding two distinct digits,
// return -1.
// Since there are only 10 possible digits ('0' to '9'),
// checking each digit is efficient.

// Time Complexity: O(10 × n) = O(n)
// Space Complexity: O(1)


class Solution {
    public int secondHighest(String s) {
        int digitsFound = 0;

        for (char c = '9'; c >= '0'; c--) {
            if (s.indexOf(c) != -1) {
                digitsFound++;

                if (digitsFound == 2) {
                    return c - '0';
                }
            }
        }

        return -1;
    }
}
