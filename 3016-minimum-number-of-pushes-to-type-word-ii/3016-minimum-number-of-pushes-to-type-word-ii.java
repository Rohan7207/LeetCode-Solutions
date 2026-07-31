// Problem: Minimum Number of Pushes to Type Word II
// Link: https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii/?envType=daily-question&envId=2026-07-31
// Difficulty: Medium

// Approach:
// Since the keypad can be remapped freely, the most frequently used
// letters should require the fewest key presses.
//
// First, count the frequency of every character.
//
// Sort the frequencies in descending order so that the letters with
// the highest frequencies are assigned to the easiest positions.
//
// There are only 8 keys (2 to 9), so:
//
// - The first 8 most frequent letters occupy the first position
//   on the keys and require 1 push.
// - The next 8 letters occupy the second position and require 2 pushes.
// - The next 8 letters occupy the third position and require 3 pushes.
// - and so on.
//
// For every non-zero frequency:
//
//      pushes = (i / 8) + 1
//
// Multiply the frequency by its required pushes and add it to the answer.
//
// Finally, return the minimum total number of pushes.

// Time Complexity:
// O(26 log 26) ≈ O(1)
// (Sorting only 26 frequencies.)
//
// Space Complexity:
// O(26) ≈ O(1)


class Solution {
    public int minimumPushes(String word) {
        // Frequency Array to store count of each letters
        int[] freq = new int[26];

        // Count occurrences of each letter
        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Sort the frequncies in descending order
        Arrays.sort(freq);
        int[] sortedFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            sortedFreq[i] = freq[25 - i];
        }

        int totalPushes = 0;

        // Calculate total no.of presses
        for (int i = 0; i < 26; i++) {
            if (sortedFreq[i] == 0) break;
            
            totalPushes += ((i / 8) + 1) * sortedFreq[i];
        }

        return totalPushes;
    }
}
