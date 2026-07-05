// Problem: Non-decreasing Array
// Link: https://leetcode.com/problems/non-decreasing-array/
// Difficulty: Medium

// Approach:
// A non-decreasing array satisfies:
//      nums[i] <= nums[i+1]
// Traverse the array from left to right.
// Whenever a violation is found:
//      nums[i] > nums[i+1]
// one modification is required.
// Keep a counter for the number of violations.
//     • If violations become more than one,
//       return false immediately.
// Otherwise, greedily fix the current violation.
// There are two choices:
// 1. Lower nums[i]
//        nums[i] = nums[i+1]
//    This is safe only if:
//        i == 0
//        OR
//        nums[i-1] <= nums[i+1]
//    because the previous element should still remain
//    less than or equal to the modified value.
// 2. Otherwise,
//    raise nums[i+1]
//        nums[i+1] = nums[i]
//    This preserves the already processed prefix.
// Continue scanning the remaining array.
// If at most one modification was required,
// return true.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean checkPossibility(int[] nums) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                count++;

                if (count > 1) return false;

                if (i == 0 || nums[i - 1] <= nums[i + 1]) {
                    nums[i] = nums[i + 1];
                } else {
                    nums[i + 1] = nums[i];
                }
            }
        }

        return true;
    }
}
