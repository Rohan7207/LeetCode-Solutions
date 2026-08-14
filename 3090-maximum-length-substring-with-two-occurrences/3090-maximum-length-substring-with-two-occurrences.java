// Problem : Maximum Length Substring With Two Occurrences
// Link : https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/?envType=daily-question&envId=2026-08-14
// Difficulty : Easy

// Approach:
// Use the Sliding Window technique with a frequency array.
//
// Maintain a window [left ... right] where every character appears
// at most twice.
//
// 1. Expand the window by moving `right` and increase the frequency
//    of the current character.
//
// 2. If the current character appears more than 2 times, the window
//    becomes invalid.
//
// 3. Move `left` forward and decrease the frequency of each removed
//    character until the current character appears at most twice.
//
// 4. Once the window becomes valid, update the maximum length.
//
// The window always maintains the condition:
//     frequency of every character <= 2

// Time Complexity: O(n)
// Space Complexity: O(1)
// Since the frequency array contains only 26 characters.


class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int maxLength = 0;

        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            count[ch - 'a']++;

            while (count[ch - 'a'] > 2) {
                char leftChar = s.charAt(left);
                count[leftChar - 'a']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
