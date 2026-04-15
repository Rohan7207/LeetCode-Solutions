// Problem: Palindrome Number
// Link: https://leetcode.com/problems/palindrome-number/
// Difficulty: Easy

// Approach:
// Reverse the given number by extracting digits one by one.
// Compare the reversed number with the original number.
// If both are equal, return true; otherwise, return false.

// Time Complexity: O(log₁₀(n))

// Space Complexity: O(1)

class Solution {
    public boolean isPalindrome(int x) {
        int temp = x;
        int rev = 0;
        
        while(x > 0){
            rev = rev * 10 + (x % 10);
            x /= 10;
        }

        return temp == rev ? true : false;
    }
}
