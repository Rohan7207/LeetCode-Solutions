// Problem: Find Words That Can Be Formed by Characters
// Link: https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
// Difficulty: Easy

// Approach:
// Use frequency arrays to compare the characters required by each word
// with the characters available in chars.
//
// 1. Count the frequency of every character in chars.
//
// 2. For every word:
//      - Create a separate frequency array `need`.
//      - Count how many times each character is required by the word.
//
// 3. Compare `need` with `freq`:
//      - If need[i] > freq[i], the word cannot be formed.
//      - Otherwise, the word can be formed.
//
// 4. If the word can be formed, add its length to the answer.

// Time Complexity: O(chars.length + 26 × words.length + total word lengths)
// Space Complexity: O(26) → O(1)


class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] freq = new int[26];

        for(char c : chars.toCharArray()) {
            freq[c - 'a']++;
        }

        int ans = 0;
        for(String word : words) {
            int[] need = new int[26];

            for(char c : word.toCharArray()) {
                need[c - 'a']++;
            }

            boolean flag = true;
            for(int i = 0; i < 26; i++) {
                if(need[i] > freq[i]) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                ans += word.length();
            }
        }

        return ans;
    }
}
