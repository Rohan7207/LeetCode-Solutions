// Problem: Detect Capital
// Link: https://leetcode.com/problems/detect-capital/
// Difficulty: Easy

// Approach:
// Count the number of uppercase letters
// present in the word.
// The capitalization is valid if:
//     - All letters are uppercase.
//     - No letters are uppercase.
//     - Exactly one letter is uppercase
//       and it is the first character.
// Check these three conditions and
// return true if any of them holds.
// Otherwise return false.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean detectCapitalUse(String word) {
        int capitals = 0;

        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if(ch >= 'A' && ch <= 'Z') capitals++;
        }

        if(capitals == word.length()) {
            return true;
        } else if(capitals == 1 && Character.isUpperCase(word.charAt(0))) {
            return true;
        } else if(capitals == 0) {
            return true;
        }

        return false;
    }
}
