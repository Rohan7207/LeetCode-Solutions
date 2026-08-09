// Problem: Complement of Base 10 Integer
// Link: https://leetcode.com/problems/complement-of-base-10-integer/
// Difficulty: Easy

// Approach:
// The complement is obtained by flipping every bit in the binary
// representation of n.
//
// 1. Handle n == 0 separately because 0 has binary representation "0"
//    and its complement is 1.
//
// 2. Build a mask containing only 1s with the same number of bits as n.
//
//    For every bit of n:
//    - Shift the mask left by one position.
//    - Add 1 to the mask.
//
//    This produces:
//
//    1 → 11 → 111 → 1111 → ...
//
// 3. XOR n with the mask.
//
//    XOR with 1 flips a bit:
//
//        0 ^ 1 = 1
//        1 ^ 1 = 0
//
// 4. Return the XOR result.

// Time Complexity: O(log n)
// Space Complexity: O(1)


class Solution {
    public int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }

        int mask = 0;
        int temp = n;

        while (temp > 0) {
            mask = (mask << 1) | 1;
            temp >>= 1; 
        }

        return mask ^ n;
    }
}
