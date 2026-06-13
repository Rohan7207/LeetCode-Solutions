// Problem: Weighted Word Mapping
// Link: https://leetcode.com/problems/weighted-word-mapping/?envType=daily-question&envId=2026-06-13
// Difficulty: Easy

// Approach:
// For each word, calculate its total weight.
// The weight of a character is given by:
//     weights[ch - 'a']
// Traverse every character of the word
// and accumulate its weight.
// After calculating the total weight:
//     rem = totalWeight % 26
// Convert this remainder into a character
// using reverse alphabet mapping:
//     0  -> 'z'
//     1  -> 'y'
//     2  -> 'x'
//     ...
//     25 -> 'a'
// This can be directly computed as:
//     (char) ('z' - rem)
// Append the mapped character to the answer.
// Repeat for every word and return the
// final string.

// Time Complexity: O(total characters in all words)
// Space Complexity: O(m)
//
// where m = number of words


class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder ans = new StringBuilder();
        int rem = 0;

        for (String word : words) {
            int sum = 0;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                sum += weights[ch - 'a'];
            }

            rem = sum % 26;

            ans.append((char) ('z' - rem));
        }

        return ans.toString();
    }
}
