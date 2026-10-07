// Problem: Check If a Word Occurs As a Prefix of Any Word in a Sentence
// Link: https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/
// Difficulty: Easy

// Approach:
// 1. Split the sentence into individual words.
// 2. Traverse the words from left to right.
// 3. Check whether the current word starts with searchWord.
// 4. If it does, return its 1-based position.
// 5. If no word matches, return -1.

// Time Complexity: O(n * m)
// Space Complexity: O(n)


class Solution {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");

        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(searchWord)) {
                return i + 1;
            }
        }

        return -1;
    }
}
