// Problem: Monotonic Array
// Link: https://leetcode.com/problems/monotonic-array/
// Difficulty: Easy

// Approach:
// A monotonic array can be either:
//     1. Monotone Increasing
//            nums[i] >= nums[i-1]
//     2. Monotone Decreasing
//            nums[i] <= nums[i-1]
// Use two boolean flags:
//     increasing = true
//     decreasing = true
// First Traversal:
//     Check whether the array is completely
//     non-decreasing.
//     If any element is smaller than its previous
//     element,
//     then increasing becomes false.
// Second Traversal:
//     Check whether the array is completely
//     non-increasing.
//     If any element is larger than its previous
//     element,
//     then decreasing becomes false.
// If either increasing or decreasing remains true,
// the array is monotonic.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                increasing = false;
                break;
            }
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                decreasing = false;
                break;
            }
        }

        return decreasing || increasing;
    }
}
