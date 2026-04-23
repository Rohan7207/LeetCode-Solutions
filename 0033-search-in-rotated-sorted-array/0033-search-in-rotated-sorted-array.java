// Problem: Search in Rotated sorted Array
// Link: https://leetcode.com/problems/search-in-rotated-sorted-array/
// Difficulty: Medium

// Approach:
// Use modified binary search to find the target in the rotated sorted array.
// In each iteration:
//     - Find the middle element.
//     - Check which half of the array is sorted.
//     - If the target lies within the sorted half, search there.
//     - Otherwise, search in the opposite half.
// Continue until the target is found or the search space becomes empty.

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
