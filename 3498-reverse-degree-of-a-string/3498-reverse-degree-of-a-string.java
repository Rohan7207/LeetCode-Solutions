// Problem: Reverse Degree of a String
// Link: https://leetcode.com/problems/reverse-degree-of-a-string/?envType=daily-question&envId=2026-09-20
// Difficulty: Easy

// Approach:
// Use Character Value + Position Multiplication.
//
// 1. Traverse the string using a 1-based position.
// 2. Convert each character into its reverse alphabetical value:
//    'a' → 26, 'b' → 25, ..., 'z' → 1.
// 3. Multiply the reverse value by the character's position.
// 4. Add the product to sum.
// 5. Return the total reverse degree.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int reverseDegree(String s) {
        int sum = 0;

        for (int i = 1; i <= s.length(); i++) {
            int val = 26 - (s.charAt(i - 1) - 'a');
            int product = val * i;

            sum += product;
        }

        return sum;
    }
}
