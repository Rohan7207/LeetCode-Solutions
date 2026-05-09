// Problem: Longest Consecutive Sequence
// Link: https://leetcode.com/problems/longest-consecutive-sequence/
// Difficulty: Medium

// Approach:
// Sort the array first so consecutive numbers
// appear next to each other.
// Traverse the sorted array:
//     - If current element is previous + 1,
//       increment current streak length.
//     - If current element is duplicate,
//       ignore it.
//     - Otherwise start a new streak.
// Track the maximum streak length during traversal.
// Return the maximum consecutive sequence length.

// Time Complexity: O(n log n)
// Space Complexity: O(1) or O(log n) depending on sorting


class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        Arrays.sort(nums);
        int curr = 1;
        int max = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                curr++;
            } else if (nums[i] != nums[i - 1]) {
                curr = 1;
            }

            max = Math.max(max, curr);
        }

        return max;
    }
}
