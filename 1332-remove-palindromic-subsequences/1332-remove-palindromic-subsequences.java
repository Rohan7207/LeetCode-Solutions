// Problem: Remove Palindromic Subsequences
// Link: https://leetcode.com/problems/remove-palindromic-subsequences/
// Difficulty: Easy

// Approach:
// Use Two Pointers + Palindrome Check.
//
// 1. Check whether the given string is a palindrome using two pointers.
//
// 2. Start one pointer from the beginning and the other from the end.
//
// 3. Compare the characters at both pointers.
//    If they differ, the string is not a palindrome.
//
// 4. If the entire string is a palindrome, it can be removed
//    in one step.
//
// 5. Otherwise, since the string contains only 'a' and 'b',
//    remove all 'a' characters as one palindromic subsequence
//    and all 'b' characters as another.
//
// 6. Therefore, a non-palindromic string always requires 2 steps.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int removePalindromeSub(String s) {
        if (isPalindrome(s)) {
            return 1;
        }

        return 2;
    }

    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }
}
