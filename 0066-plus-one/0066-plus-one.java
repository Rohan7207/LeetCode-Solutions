// Problem: Plus One
// Link: https://leetcode.com/problems/plus-one/
// Difficulty: Easy

// Approach:
// Traverse the digits array from right to left
// to simulate addition of one.
//     - If the current digit is less than 9,
//       increment it and return the array
//       because no further carry is needed.
//     - Otherwise, set the current digit to 0
//       and continue propagating the carry.
// If all digits become 0 (example: 999),
// create a new array with one extra digit,
// set the first element to 1,
// and return the new array.

// Time Complexity: O(n)
// Space Complexity: O(1)
// (excluding output array in overflow case)


class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
