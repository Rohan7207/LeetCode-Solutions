// Problem: Ransom Note
// Link: https://leetcode.com/problems/ransom-note/
// Difficulty: Easy

// Approach:
// Use frequency counting to track available characters in the magazine.
//
// 1. Count the frequency of every character in magazine.
// 2. Traverse ransomNote character by character.
// 3. If the required character has frequency 0, it cannot be formed.
// 4. Otherwise, consume one occurrence by decrementing its frequency.
// 5. If all characters are successfully consumed, return true.

// Time Complexity: O(n + m)
// Space Complexity: O(1)
// Since the frequency array always has 26 elements.


class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] freq = new int[26];

        for (char ch : magazine.toCharArray()) {
            freq[ch - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char ch = ransomNote.charAt(i);
            if (freq[ch - 'a'] <= 0) {
                return false;
            }

            freq[ch - 'a']--;
        }


        return true;
    }
}
