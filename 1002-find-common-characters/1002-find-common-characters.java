// Problem: Find Common Characters
// Link: https://leetcode.com/problems/find-common-characters/
// Difficulty: Easy

// Approach:
// The goal is to find all characters that appear in every word,
// including duplicates.
//
// Step 1:
// Count the frequency of every character in the first word.
// These frequencies represent the maximum possible common
// characters initially.
//
// Step 2:
// Process each remaining word one by one.
//
// - Reset the current frequency array.
// - Count the frequency of every character in the current word.
// - Update the common frequency array by taking the minimum
//   frequency for every character.
//
// This ensures that after processing each word,
// commonCharacterCounts[c] stores the number of times character
// c appears in every word seen so far.
//
// Step 3:
// Traverse the final common frequency array.
//
// For every character whose frequency is greater than zero,
// add that character to the answer exactly that many times.
//
// The resulting list contains every common character,
// including duplicates.

// Time Complexity:
// O(N × L + 26 × N)
// ≈ O(N × L)
//
// where
// N = number of words
// L = average length of each word.
//
// Space Complexity:
// O(26)
// (Two fixed-size frequency arrays.)


class Solution {
    public List<String> commonChars(String[] words) {
        int wordSize = words.length;
        int[] commonCharacterCounts = new int[26];
        int[] currentCharacterCounts = new int[26];
        List<String> res = new ArrayList<>();

        // Initialize commonCharacterCounts with the characters from the first word
        for (char ch : words[0].toCharArray()) {
            commonCharacterCounts[ch - 'a']++;
        }

        for (int i = 1; i < wordSize; i++) {
            Arrays.fill(currentCharacterCounts, 0);

            // Count characters in the current word
            for (char ch : words[i].toCharArray()) {
                currentCharacterCounts[ch - 'a']++;
            }

            // Update the common character counts to keep the minimum counts
            for (int letter = 0; letter < 26; letter++) {
                commonCharacterCounts[letter] = Math.min(commonCharacterCounts[letter], currentCharacterCounts[letter]);
            }
        }

        // Collect the common characters based on the final counts
        for (int letter = 0; letter < 26; letter++) {
            for (int commonCount = 0; commonCount < commonCharacterCounts[letter]; commonCount++) {
                res.add(String.valueOf((char) (letter + 'a')));
            }
        }

        return res;
    }
}
