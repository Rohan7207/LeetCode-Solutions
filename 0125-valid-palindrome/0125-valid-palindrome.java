// Problem: Valid Palindrome
// Link: https://leetcode.com/problems/valid-palindrome/
// Difficulty: Easy

// Approach:
// Use two pointers starting from left and right ends
// of the string.
// Traverse while left < right:
//     - Skip non-alphanumeric characters from left.
//     - Skip non-alphanumeric characters from right.
//     - Convert both characters to lowercase
//       and compare them.
//     - If characters do not match, return false.
//     - Otherwise move both pointers inward.
// If traversal completes without mismatch,
// return true.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
