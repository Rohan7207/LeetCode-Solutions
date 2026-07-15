// Problem: Smallest Range I
// Link: https://leetcode.com/problems/smallest-range-i/
// Difficulty: Easy

// Approach:
// The score of the array depends only on its minimum
// and maximum elements:
//     score = max - min
// To minimize this score:
//     • Increase the minimum element by k.
//     • Decrease the maximum element by k.
// These operations bring the two extreme values
// as close as possible.
// Let:
//     newMin = min + k
//     newMax = max - k
// The minimum possible score becomes:
//     newMax - newMin
// If the adjusted minimum becomes greater than or equal
// to the adjusted maximum, the range can be reduced to 0.
// Since the score cannot be negative, return:
//     max(0, newMax - newMin)

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int smallestRangeI(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int newMin = min + k;
        int newMax = max - k;

        return Math.max(0, newMax - newMin);
    }
}
