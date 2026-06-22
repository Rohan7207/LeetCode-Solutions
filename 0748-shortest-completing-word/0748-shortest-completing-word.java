// Problem: Shortest Completing Word
// Link: https://leetcode.com/problems/shortest-completing-word/
// Difficulty: Easy

// Approach:
// We need to find the shortest word that completes the given licensePlate.
// A completing word must contain all letters from licensePlate.
// Digits, spaces, and symbols in licensePlate are ignored.
// Uppercase and lowercase letters are treated the same.
// First, create a frequency array requiredFreq[26].
// This stores how many times each letter is needed from licensePlate.
// While reading licensePlate:
//     if the character is a letter:
//         convert it to lowercase
//         increase its frequency in requiredFreq
// Then check every word in words.
// For each word:
//     create currentFreq[26]
//     count the frequency of every character in that word
// Now compare currentFreq with requiredFreq.
// A word is valid only if:
//     currentFreq[i] >= requiredFreq[i]
// for every letter from 'a' to 'z'.
// If the word is valid, then it is a completing word.
// Since we need the shortest completing word,
// update answer only if:
//     answer is empty
//     OR current word length is smaller than answer length
// We do not update for equal length,
// because if two words have the same length,
// the problem asks to return the first one in the array.
// Finally, return answer.

// Time Complexity: O(L + total characters in words)
// Space Complexity: O(1)


class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] requiredFreq = new int[26];

        for (char ch : licensePlate.toCharArray()) {
            if (Character.isLetter(ch)) {
                ch = Character.toLowerCase(ch);
                requiredFreq[ch - 'a']++;
            }
        }

        String ans = "";
        for (String word : words) {
            int[] currentFreq = new int[26];
            for (char ch : word.toCharArray()) {
                currentFreq[ch - 'a']++;
            }

            boolean valid = true;

            for (int i = 0; i < 26; i++) {
                if (currentFreq[i] < requiredFreq[i]) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                if (ans.equals("") || word.length() < ans.length()) {
                    ans = word;
                }
            }
        }

        return ans;
    }
}
