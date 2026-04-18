// Problem: Mirror Distance of an Integer
// Link: https://leetcode.com/problems/mirror-distance-of-an-integer/?envType=daily-question&envId=2026-04-18
// Difficulty: Easy

// Approach:
// Reverse the given number by extracting digits one by one.
// Compute the absolute difference between the original number and its reversed number.
// Return the result.

// Time Complexity: O(log₁₀(n))

// Space Complexity: O(1)

class Solution {
    public int mirrorDistance(int n) {
        int x = n;
        int rev = 0;

        while(x != 0) {
            rev = rev * 10 + (x % 10);
            x /= 10;
        }

        return Math.abs(n - rev);
    }
}
