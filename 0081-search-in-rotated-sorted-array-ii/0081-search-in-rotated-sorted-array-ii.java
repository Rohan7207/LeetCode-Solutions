// Problem: Search in Rotated Sorted Array II
// Link: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
// Difficulty: Medium

// Approach:
// Use modified binary search to search
// in a rotated sorted array with duplicates.
// Traverse while left <= right:
//     - If mid value equals target, return true.
//     - If nums[left] == nums[mid],
//       increment left to skip duplicates
//       because sorted half cannot be identified.
//     - Check which half is sorted.
//         - If left half is sorted,
//           check whether target lies in that range.
//         - Otherwise right half is sorted,
//           check whether target lies there.
//     - Move left or right pointers accordingly.
// Return false if target is not found.

// Time Complexity: O(log n) average case
// Worst Case: O(n) due to duplicates
// Space Complexity: O(1)


class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }

            if (nums[mid] == nums[left]) {
                left++;
                continue;
            }

            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return false;
    }
}
