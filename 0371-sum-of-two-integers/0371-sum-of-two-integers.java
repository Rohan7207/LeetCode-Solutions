// Problem: Sum of Two Integers
// Link: https://leetcode.com/problems/sum-of-two-integers/
// Difficulty: Medium

// Approach:
// Use Bit Manipulation.
// XOR (^)
//     gives sum without carry.
// AND (&)
//     finds positions where carry occurs.
// Shift carry left by 1
//     because carry affects the next bit.
// Repeat until carry becomes 0.

// Time Complexity: O(32)
// Space Complexity: O(1)


class Solution {
    public int getSum(int a, int b) {
        while(b != 0){
            int temp = a ^ b;
            int carry = (a & b) << 1;

            a = temp;
            b = carry;
        }

        return a;
    }
}
