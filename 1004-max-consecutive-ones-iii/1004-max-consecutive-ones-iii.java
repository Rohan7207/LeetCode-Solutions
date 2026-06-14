// Problem: Max Consecutive Ones III
// Link: https://leetcode.com/problems/max-consecutive-ones-iii/
// Difficulty: Medium

// Approach:
// Find the longest subarray that can become
// all 1's after flipping at most k zeros.
// Use variable-size sliding window.
// Maintain:
//     left      -> start of window
//     right     -> end of window
//     zeroCount -> number of zeros in window
// Expand the window by moving right.
// If nums[right] is 0:
//     - Increase zeroCount
// If zeroCount becomes greater than k:
//     - Window is invalid
//     - Move left forward until zeroCount <= k
//     - If nums[left] is 0, decrease zeroCount
// For every valid window:
//     - Length = right - left + 1
//     - Update maxLength
// Return maxLength.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int maxLength = 0;
        int zeroCount = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            while (zeroCount > k) {
                if (nums[left] == 0) zeroCount--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
