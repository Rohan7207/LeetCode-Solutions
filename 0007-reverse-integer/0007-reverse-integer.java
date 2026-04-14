// Problem: Reverse Integer
// Link: https://leetcode.com/problems/reverse-integer/
// Difficulty: Medium

// Approach:
// Extract digits one by one and build the reversed number.
// Before updating, check for overflow to stay within integer range.

// Time Complexity: O(logn)

// Space Complexity: O(1)

class Solution {
    public int reverse(int x) {
        int rev = 0;

        while(x != 0){
            if(rev > 214748364 || rev < -214748364) return 0;
            rev = rev * 10 + (x % 10);
            x /= 10;
        }
        return rev;
    }
}
