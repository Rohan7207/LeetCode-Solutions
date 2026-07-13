// Problem: Count Numbers With Unique Digits
// Link: https://leetcode.com/problems/count-numbers-with-unique-digits/
// Difficulty: Medium

// Approach:
// Instead of generating every number from 0 to 10ⁿ-1,
// count how many unique-digit numbers exist for each digit length.
// Count numbers by their length:
//     1-digit:
//         10 numbers (0-9)
//     2-digit:
//         First digit  : 9 choices (1-9)
//         Second digit : 9 choices (0-9 except first)
//         Count = 9 × 9
//     3-digit:
//         First digit  : 9 choices
//         Second digit : 9 choices
//         Third digit  : 8 choices
//         Count = 9 × 9 × 8
// Continue this pattern until n digits.
// Algorithm:
//     1. If n == 0, only the number 0 exists.
//     2. Initialize:
//            ans = 10      // All 1-digit numbers
//            curr = 9      // First digit choices
//            available = 9 // Choices for the next position
//     3. For every digit length from 2 to n:
//            curr *= available
//            ans += curr
//            available--
//     4. Return ans.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0)
            return 1;

        int ans = 10; // All 1-digit numbers (0-9)
        int curr = 9; // First digit choices for numbers with length >= 2
        int available = 9; // Remaining choices for the second digit

        for (int digit = 2; digit <= n; digit++) {
            curr *= available;
            ans += curr;
            available--;
        }

        return ans;
    }
}
