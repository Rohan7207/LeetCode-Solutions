// Problem: Increasing Decreasing String
// Link: https://leetcode.com/problems/increasing-decreasing-string/
// Difficulty: Easy

// Approach:
// Use frequency counting + alternating alphabet traversal.
//
// 1. Count the frequency of each character using a 26-sized array.
// 2. Build the answer while characters are still remaining.
// 3. In the ascending pass, scan 'a' → 'z' and append each available
//    character once.
// 4. Decrease its frequency after using it.
// 5. In the descending pass, scan 'z' → 'a' and do the same.
// 6. Repeat ascending and descending passes until all characters are used.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public String sortString(String s) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder res = new StringBuilder();

        while (res.length() < s.length()) {
            // ascending
            for (int i = 0; i < 26; i++) {
                if (freq[i] > 0) {
                    res.append((char) ('a' + i));
                    freq[i]--;
                }
            }

            // descending
            for (int i = 25; i >= 0; i--) {
                if (freq[i] > 0) {
                    res.append((char) ('a' + i));
                    freq[i]--;
                }
            }
        }

        return res.toString();
    }
}
