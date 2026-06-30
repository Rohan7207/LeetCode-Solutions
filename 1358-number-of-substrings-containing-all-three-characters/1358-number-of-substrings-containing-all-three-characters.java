// Problem: Number of Substrings Containing All Three Characters
// Link: https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/?envType=daily-question&envId=2026-06-30
// Difficulty: Medium

// Approach:
// Count the number of substrings that contain all three characters:
// 'a', 'b', and 'c'.
// Use Sliding Window + Two Pointers.
// Maintain:
//     left  -> starting index of current window
//     right -> ending index of current window
// Also maintain frequency of characters:
//     count[0] -> frequency of 'a'
//     count[1] -> frequency of 'b'
//     count[2] -> frequency of 'c'
// Step 1:
// Expand the window by moving right pointer.
//     Add current character frequency.
// Step 2:
// When the current window contains all three characters:
//     a > 0
//     b > 0
//     c > 0
//     All substrings starting from left and ending from right
//     to the end of the string are valid.
//     Number of such substrings:
//          n - right
//     Add them to answer.
// Step 3:
// Shrink the window from the left:
//     Remove s[left] frequency
//     Move left forward
//     Continue shrinking while the window is still valid.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int numberOfSubstrings(String s) {
        int ans = 0;
        int n = s.length();

        int[] count = new int[3];
        int left = 0;

        for (int right = 0; right < n; right++) {
            count[s.charAt(right) - 'a']++;

            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                ans += n - right;

                count[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return ans;
    }
}
