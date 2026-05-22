// Problem: Search in Rotated Sorted Array
// Link: https://leetcode.com/problems/search-in-rotated-sorted-array/description/?envType=daily-question&envId=2026-05-22
// Difficulty: Medium

// Approach:
// Use Binary Search on the rotated sorted array.
// For every mid element:
//     - If target is found, return its index.
//     - Check which half is sorted.
//     - If left half is sorted:
//           - Check whether target lies within
//             the left sorted range.
//           - If yes, search left half.
//           - Otherwise search right half.
//     - If right half is sorted:
//           - Check whether target lies within
//             the right sorted range.
//           - If yes, search right half.
//           - Otherwise search left half.
// Continue until search space becomes empty.
// Return -1 if target is not found.

// Time Complexity: O(log n)
// Space Complexity: O(1)


class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            if(nums[mid] == target) {
                return mid;
            } else if(nums[low] <= nums[mid]) {
                if(nums[low] <= target && target <= nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if(nums[mid] <= target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }
}
