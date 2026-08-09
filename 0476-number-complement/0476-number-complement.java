// Problem: Number Complement
// Link: https://leetcode.com/problems/number-complement/
// Difficulty: Easy

// Approach:
// The complement of a number is obtained by flipping all the bits
// in its binary representation.
//
// 1. Handle num == 0 separately because its complement is 1.
//
// 2. Create a mask containing the same number of 1s as the number
//    of bits present in num.
//
// 3. Use temp to determine the number of bits in num.
//    For every bit:
//    - Shift mask left by 1.
//    - Add 1 to mask.
//    - Right-shift temp to process the next bit.
//
// 4. XOR the mask with num.
//    Since XOR with 1 flips a bit, this flips every bit of num.
//
// 5. Return the resulting value.

// Time Complexity: O(log n)
// Space Complexity: O(1)


class Solution {
    public int findComplement(int num) {
        if (num == 0) {
            return 1;
        }

        int mask = 0;
        int temp = num;

        while (temp > 0) {
            mask = (mask << 1) | 1;
            temp >>= 1;
        }

        return mask ^ num;
    }
}
