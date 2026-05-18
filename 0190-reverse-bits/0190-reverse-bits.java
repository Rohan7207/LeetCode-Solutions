// Problem: Reverse Bits
// Link: https://leetcode.com/problems/reverse-bits/
// Difficulty: Easy

// Approach:
// Reverse the bits of a 32-bit integer.
// Traverse all 32 bits of the number one by one.
// For every iteration:
//     - Left shift rev by 1
//           -> creates space for next bit
//     - Extract the last bit of n using:
//           (n & 1)
//     - Add extracted bit to rev using OR operation
//     - Right shift n by 1
//           -> move next bit to the last position
// Repeat this process 32 times to reverse all bits.

// Time Complexity: O(32) ≈ O(1)
// Space Complexity: O(1)


class Solution {
    public int reverseBits(int n) {
        int rev = 0;

        for(int i = 0; i < 32; i++){
            rev = rev << 1;  
            rev = rev | (n & 1);
            n = n >> 1; 
        }

        return rev;   
    }
}
