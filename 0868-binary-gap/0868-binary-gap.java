// Problem: Binary Gap
// Link: https://leetcode.com/problems/binary-gap/
// Difficulty: Easy

// Approach:
// We need to find the maximum distance between
// consecutive set bits (1s) in the binary
// representation of the number.
// Instead of converting the number into a binary
// string, scan each of the 32 bits directly using
// a moving bitmask.
// Initialize:
//     mask = 1
// which represents:
//     000...0001
// The mask checks one bit at a time.
// For every bit position:
//     If (mask & n) == mask,
//     the current bit is set.
//     • If this is the first set bit,
//       simply store its position.
//     • Otherwise,
//       compute the distance between the current
//       set bit and the previous set bit.
//       Update the maximum distance if needed.
//       Then update the previous position.
// After checking the current bit,
// left shift the mask by one position
// to examine the next bit.
// Continue until all 32 bits are processed.

// Time Complexity: O(32) = O(1)
// Space Complexity: O(1)


class Solution {
    public int binaryGap(int n) {
        int res = 0;
        boolean found = false;
        int a = 1;
        int pos = -1;

        for (int i = 0; i < 32; i++) {
            if ((a & n) == a) {
                if (found) {
                    int d = i - pos;
                    if (d > res)
                        res = d;
                    pos = i;
                } else {
                    pos = i;
                    found = true;
                }
            }

            a = a << 1; 
        }

        return res;
    }
}
