// Problem: Replace the Substring for Balanced String
// Link: https://leetcode.com/problems/replace-the-substring-for-balanced-string/
// Difficulty: Medium

// Approach:
// Use Sliding Window + Frequency Counting.
//
// 1. A balanced string of length n must have exactly n/4
//    occurrences of Q, W, E and R.
//
// 2. Count the frequency of all characters in the string.
//
// 3. First check whether the string is already balanced.
//    If every character count is <= n/4, return 0.
//
// 4. The sliding-window represents the substring that we
//    will REPLACE.
//
// 5. Initially, count[] represents the whole string.
//
// 6. Move `right` and put s[right] inside the window.
//    Therefore, decrease its frequency from count[]:
//
//        count[index(s.charAt(right))]--
//
//    Now count[] represents only the characters OUTSIDE
//    the current window.
//
// 7. The outside is valid when every character occurs at
//    most n/4 times.
//
// 8. While the outside is valid, shrink the window from
//    the left because we want the minimum-length window.
//
// 9. For every valid window, update:
//
//        ans = min(ans, window length)
//
// 10. Return ans.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int balancedString(String s) {
        int n = s.length();
        int target = n / 4;
        int[] count = new int[4];

        for (char c : s.toCharArray()) {
            count[index(c)]++;
        }

        boolean balanced = true;

        for (int c : count) {
            if (c > target) {
                balanced = false;
                break;
            }
        }

        if (balanced) {
            return 0;
        }

        int left = 0;
        int ans = n;
        for (int right = 0; right < n; right++) {
            // Remove s[right] from outside
            count[index(s.charAt(right))]--;

            // check whether outside is valid
            while (left <= right && isValid(count, target)) {
                ans = Math.min(ans, right - left + 1);

                // put s[left] back outside
                count[index(s.charAt(left))]++;
                left++;
            }
        }

        return ans;
    }

    private boolean isValid(int[] count, int target) {
        return count[0] <= target &&
                count[1] <= target &&
                count[2] <= target &&
                count[3] <= target;
    }

    private int index(char c) {
        switch (c) {
            case 'Q':
                return 0;
            case 'W':
                return 1;
            case 'E':
                return 2;
            default:
                return 3;
        }
    }
}
