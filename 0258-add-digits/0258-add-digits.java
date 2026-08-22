// Problem: Add Digits
// Link: https://leetcode.com/problems/add-digits/
// Difficulty: Easy

// Approach:
// Use the mathematical property called the Digital Root.
//
// 1. Repeatedly adding the digits of a number until one digit remains
//    gives its digital root.
//
// 2. For any positive number, the digital root is:
//      1 + (num - 1) % 9
//
// 3. Handle num = 0 separately because the formula would return 9.
//
// 4. Return the calculated digital root.

// Time Complexity: O(1)
// Space Complexity: O(1)


class Solution {
    public int addDigits(int num) {
        // Use digital root property: repeated digit summation of a positive integer is equivalent to 1 + (num - 1) % 9
        if (num == 0) {
            return 0;
        }

        return 1 + (num - 1) % 9;
    }
}
