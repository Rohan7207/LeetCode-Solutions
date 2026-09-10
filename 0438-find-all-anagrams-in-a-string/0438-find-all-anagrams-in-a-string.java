// Problem: Find All Anagrams in a String
// Link: https://leetcode.com/problems/find-all-anagrams-in-a-string/
// Difficulty: Medium

// Approach:
// Use a fixed-size sliding window + frequency counting.
//
// 1. Count the frequency of every character in pattern p.
// 2. Maintain a window of size p.length() over string s.
// 3. Add the right character to the current window.
// 4. If the window becomes larger than p.length(), remove the
//    leftmost character and move left forward.
// 5. Whenever the window size equals p.length(), compare its
//    frequency array with p's frequency array.
// 6. If both frequency arrays match, the window is an anagram,
//    so add its starting index to the answer.

// Time Complexity: O(26n) = O(n)
// Space Complexity: O(1)
// Since both frequency arrays contain only 26 elements.


class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int[] freqP = new int[26];

        if (p.length() > s.length()) {
            return ans;
        }

        for (char ch : p.toCharArray()) {
            freqP[ch - 'a']++;
        }

        int left = 0;
        int[] freq = new int[26];
        int windowSize = 0;
        int n = p.length();
        for (int right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'a']++;
            windowSize++;

            if (windowSize > n) {
                freq[s.charAt(left) - 'a']--;
                left++;
                windowSize--;
            }

            if (windowSize == n) {
                boolean flag = true;
                for (int i = 0; i < 26; i++) {
                    if (freq[i] != freqP[i]) {
                        flag = false;
                    }
                }

                if (flag) {
                    ans.add(left);
                }
            }
        }

        return ans;
    }
}
