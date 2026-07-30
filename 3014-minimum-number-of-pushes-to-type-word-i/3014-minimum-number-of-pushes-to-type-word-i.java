// Problem: Minimum Number of Pushes to Type Word I
// Link: https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-i/?envType=daily-question&envId=2026-07-30
// Difficulty: Easy

// Approach:
// Since the keypad has only 8 usable keys (2 to 9), at most 8 letters
// can occupy the first position on the keys.
// The typing cost depends only on the position of a letter within its key:
// - First 8 letters  -> 1 push each
// - Next 8 letters   -> 2 pushes each
// - Next 8 letters   -> 3 pushes each
// - and so on.
// The actual mapping of letters to keys is unnecessary because the
// problem asks only for the minimum total pushes, not the mapping.
// Traverse the letters of the word.
// For the i-th letter (0-indexed), its required pushes are:
//      (i / 8) + 1
// since every group of 8 letters increases the push count by one.
// Sum these push counts for all characters and return the result.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int minimumPushes(String word) {
        int count = 0;

        for(int i = 0; i < word.length(); i++) {
            count += (i / 8) + 1;
        }

        return count;
    }
}
