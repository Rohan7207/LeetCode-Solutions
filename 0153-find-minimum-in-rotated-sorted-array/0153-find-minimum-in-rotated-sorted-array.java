// Problem: Find Minimum in Rotated Sorted Array
// Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/?envType=daily-question&envId=2026-05-15
// Difficulty: Medium

// Approach:
// Use Binary Search to identify the sorted half
// of the rotated array.
// If left half is sorted:
//     - Update answer with leftmost element.
//     - Search in right half.
// Otherwise:
//     - Minimum lies in left half including mid.
//     - Update answer with mid element.
// Continue until search space is exhausted.
// Return the minimum element found.

// Time Complexity: O(log n)
// Space Complexity: O(1)


class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int h = nums.length - 1;
        int ans = Integer.MAX_VALUE;

        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (nums[l] <= nums[mid]) {
                ans = Math.min(ans, nums[l]);
                l = mid + 1;
            } else {
                ans = Math.min(ans, nums[mid]);
                h = mid - 1;
            }
        }
        
        return ans;
    }
}
