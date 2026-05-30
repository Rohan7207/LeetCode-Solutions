// Problem : Power of Four
// Link : https://leetcode.com/problems/power-of-four/
// Difficulty : Easy

// Approach:
// Keep dividing the number by 4
// as long as it is divisible by 4.
// If the number is a power of 4,
// repeated division will eventually
// reduce it to 1.
// Otherwise, it will stop at some
// other value.
// Return true only if the final
// value becomes 1.

// Time Complexity: O(log₄ n)
// Space Complexity: O(1)


class Solution {
    public boolean isPowerOfFour(int n) {
        if(n <= 0) return false;

        while(n % 4 == 0) {
            n = n / 4; 
        }

        return n == 1;
    }
}
