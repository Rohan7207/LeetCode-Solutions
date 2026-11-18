// Problem: Minimum Number of Changes to Make Binary String Beautiful
// Link: https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/?envType=daily-question&envId=2026-11-17
// Difficulty: Medium

// Approach:
// 1. Divide the string into non-overlapping pairs:
//    (0,1), (2,3), (4,5), ...
//
// 2. A pair is already beautiful if both characters are equal:
//    "00" or "11".
//
// 3. If the characters are different ("01" or "10"),
//    exactly one character must be changed.
//
// 4. Count every mismatched pair.
//
// 5. Return the total count.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int minChanges(String s) {
        int n = s.length();
        int count = 0;

        for (int i = 1; i < n; i += 2) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                count++;
            }
        }

        return count;
    }
}
