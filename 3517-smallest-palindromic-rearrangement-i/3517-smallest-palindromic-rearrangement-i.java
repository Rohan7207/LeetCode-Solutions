// Problem: Smallest Palindromic Rearrangement I
// Link: https://leetcode.com/problems/smallest-palindromic-rearrangement-i/?envType=daily-question&envId=2026-07-28
// Difficulty: Medium

// Approach:
// Since the input is already a palindrome, its character frequencies satisfy:
// - Every character appears an even number of times, OR
// - Exactly one character appears an odd number of times.
// Instead of explicitly building the left half and reversing it,
// construct the final palindrome directly using two pointers.
//
// Step 1:
// Count the frequency of every character.
//
// Step 2:
// Create a character array of size n to store the answer.
//
// Maintain two pointers:
//
// - left  -> starts from index 0.
// - right -> starts from index n - 1.
//
// Step 3:
// Traverse characters from 'a' to 'z'.
//
// While a character has at least two occurrences:
//
// - Place one copy at the left pointer.
// - Place one copy at the right pointer.
// - Move both pointers inward.
// - Decrease its frequency by 2.
//
// Processing characters in alphabetical order ensures that smaller
// characters occupy the earliest positions, producing the
// lexicographically smallest palindrome.
//
// Step 4:
// After placing all pairs, if one character still has frequency 1,
// place it in the remaining middle position.
//
// Step 5:
// Convert the character array into a string and return it.

// Time Complexity: O(n + 26)
// Space Complexity: O(n)


class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        char[] ans = new char[n];
        int left = 0;
        int right = n - 1;

        for (int i = 0; i < 26; i++) {
            while (freq[i] >= 2) {
                char ch = (char) (i + 'a');
                ans[left++] = ch;
                ans[right--] = ch;
                freq[i] -= 2;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (freq[i] == 1) {
                ans[left] = (char) (i + 'a');
                break;
            }
        }

        return new String(ans);
    }
}
