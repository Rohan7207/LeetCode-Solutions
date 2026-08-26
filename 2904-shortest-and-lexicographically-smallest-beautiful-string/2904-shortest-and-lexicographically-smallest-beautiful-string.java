// Problem: Shortest and Lexicographically Smallest Beautiful String
// Link: https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/?envType=daily-question&envId=2026-08-26
// Difficulty: Medium

// Approach:
// Use Sliding Window + Greedy.
//
// 1. First count the total number of 1s.
//    If totalOnes < k, it is impossible to form a beautiful
//    substring, so return "".
//
// 2. Maintain a sliding window [left, right] and count the
//    number of 1s inside it.
//
// 3. Expand the window by moving `right`.
//
// 4. If the window has more than k ones, shrink it from the
//    left until it has exactly k ones.
//
// 5. If the leftmost character is '0', remove those leading
//    zeros as well:
//
//      while (cnt > k || s.charAt(left) == '0')
//
//    This is important because removing leading zeros makes the
//    substring shorter without changing the number of ones.
//
// 6. Once `cnt == k`, the current window is a valid beautiful
//    substring.
//
// 7. Compare it with the current answer:
//      - Prefer the shorter substring.
//      - If lengths are equal, prefer the lexicographically
//        smaller substring.
//
// 8. Return the best substring found.

// Time Complexity: O(n²) in this implementation because
//                  substring creation/comparison can take O(n).
// Space Complexity: O(n) for the created substrings.


class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int totalOne = 0;

        for (int i = 0; i < s.length(); i++) {
            totalOne += s.charAt(i) - '0'; 
        }

        if (totalOne < k) 
            return "";

        String ans = s;
        int left = 0;
        int cnt = 0;
        for (int right = 0; right < s.length(); right++) {
            cnt += s.charAt(right) - '0';

            while (cnt > k || s.charAt(left) == '0') {
                cnt -= s.charAt(left++) - '0';
            }

            if (cnt == k) {
                String temp = s.substring(left, right + 1);

                if (temp.length() < ans.length() || ((temp.length() == ans.length()) && temp.compareTo(ans) < 0)) {
                    ans = temp;
                }
            }
        }

        return ans;
    }
}
