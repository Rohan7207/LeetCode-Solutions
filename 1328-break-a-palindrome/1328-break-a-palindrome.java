// Problem: Break a Palindrome
// Link: https://leetcode.com/problems/break-a-palindrome/
// Difficulty: Medium

// Approach:
// Use Greedy + Lexicographical Order.
//
// 1. If the palindrome has only one character, return an empty string
//    because changing it would still produce a palindrome.
//
// 2. Traverse only the first half of the string.
//
// 3. Find the first character that is not 'a' and change it to 'a'.
//    Changing the earliest possible character gives the smallest
//    lexicographical result while breaking the palindrome.
//
// 4. If all characters in the first half are already 'a', change the
//    last character to 'b'. This is the smallest possible change that
//    breaks the palindrome.
//
// 5. Return the modified string.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        if (n == 1) {
            return "";
        }

        StringBuilder sb = new StringBuilder(palindrome);
        for (int i = 0; i < n / 2; i++) {
            if (sb.charAt(i) != 'a') {
                sb.setCharAt(i, 'a');

                return sb.toString();
            }
        }

        sb.setCharAt(n - 1, 'b');
        return sb.toString();
    }
}
