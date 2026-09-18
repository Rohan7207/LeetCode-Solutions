// Problem: Maximum Number of Non-Overlapping Substrings
// Link: https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/?envType=daily-question&envId=2026-09-18
// Difficulty: Hard

// Approach:
// Use Character Intervals + Greedy Selection.
//
// 1. Store the first and last occurrence of each character.
//
// 2. For every character, expand its interval to include all occurrences
//    of characters inside that interval.
//
// 3. If any character starts before the current interval's start,
//    mark the interval as invalid.
//
// 4. Traverse the string from right to left to find valid intervals.
//
// 5. Select an interval only when its start matches the current index
//    and its end is before the previously selected interval's start.
//
// 6. Add the substring to the result.

// Time Complexity: O(26 * n) → O(n)
// Space Complexity: O(26) → O(1), excluding result


class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] start = new int[26];
        int[] end = new int[26];
        boolean[] isValid = new boolean[26];

        List<String> res = new ArrayList<>();
        Arrays.fill(start, -1);
        Arrays.fill(isValid, true);

        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';

            if (start[idx] == -1) {
                start[idx] = i;
            }

            end[idx] = i;
        }

        for (int c = 0; c < 26; c++) { 
            if (start[c] == -1) {
                continue;
            }

            for (int i = start[c]; i <= end[c]; i++) {
                if (start[s.charAt(i) - 'a'] < start[c]) {
                    isValid[c] = false;
                    break;
                }

                end[c] = Math.max(end[c], end[s.charAt(i) - 'a']);
            }
        }

        int lastTakenStart = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            int idx = s.charAt(i) - 'a';

            if (!isValid[idx]) {
                continue;
            }

            if (i == start[idx] && end[idx] < lastTakenStart) {
                res.add(s.substring(i, end[idx] + 1));

                lastTakenStart = i;
            }
        }

        return res;
    }
}
