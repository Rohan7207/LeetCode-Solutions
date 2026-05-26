// Problem: Count the Number of Special Characters I
// Link: https://leetcode.com/problems/count-the-number-of-special-characters-i/?envType=daily-question&envId=2026-05-26
// Difficulty: Easy

// Approach:
// Use two boolean arrays to track whether
// each lowercase and uppercase letter appears.
// Traverse the string:
//     - If character is lowercase,
//       mark it in lower array.
//     - If character is uppercase,
//       mark it in upper array.
// After processing the string,
// iterate through all 26 letters.
// For each letter:
//     - If both lowercase and uppercase
//       versions are present,
//       count it as a special character.
//
// Return the total count.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int numberOfSpecialChars(String word) {
        boolean[] isLower = new boolean[26];
        boolean[] isUpper = new boolean[26];

        for (char ch : word.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                isLower[ch - 'a'] = true;
            } else {
                isUpper[ch - 'A'] = true;
            }
        }

        int count = 0;

        for (int i = 0; i < 26; i++) {
            if (isLower[i] && isUpper[i]) {
                count++;
            }
        }

        return count;
    }
}
