// Problem: Number of 1 Bits
// Link: https://leetcode.com/problems/number-of-1-bits/
// Difficulty: Easy

// Approach:
// Count the number of set bits using Brian Kernighan’s Algorithm.
// The expression: n = n & (n - 1)
//     - removes the rightmost set bit from the number.
// Repeat this process until n becomes 0.
// For every iteration:
//     - Remove one set bit
//     - Increment count
// Number of iterations equals the number of set bits.

// Time Complexity: O(number of set bits)
// Space Complexity: O(1)


class Solution {
    public int hammingWeight(int n) {
        int count = 0;

        while(n != 0){
            count++;
            n = n & (n - 1);
        }

        return count;
    }
}
