// Problem: Longest Strictly Increasing or Strictly Decreasing Subarray
// Link: https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/?envType=daily-question&envId=2026-11-11
// Difficulty: Easy

// Approach:
// 1. Maintain two counters:
//      increase -> length of current strictly increasing subarray.
//      decrease -> length of current strictly decreasing subarray.
//
// 2. Initialize both counters to 1 because a single element
//    is both increasing and decreasing.
//
// 3. Traverse the array from index 1.
//
// 4. Compare the current element with the previous element:
//
//      If nums[i - 1] < nums[i]:
//          Increment increase.
//          Reset decrease to 1.
//
//      If nums[i - 1] > nums[i]:
//          Increment decrease.
//          Reset increase to 1.
//
//      If both are equal:
//          Reset both counters to 1.
//
// 5. Maintain maximum increasing and decreasing lengths.
//
// 6. Return the maximum of both.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int increase = 1, decrease = 1;
        int maxIncrease = 1, maxDecrease = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                increase++;
                decrease = 1;

                maxIncrease = Math.max(maxIncrease, increase);
            } else if (nums[i - 1] > nums[i]) {
                decrease++;
                increase = 1;

                maxDecrease = Math.max(maxDecrease, decrease);
            } else {
                increase = 1;
                decrease = 1;
            }
        }

        return Math.max(maxIncrease, maxDecrease);
    }
}
