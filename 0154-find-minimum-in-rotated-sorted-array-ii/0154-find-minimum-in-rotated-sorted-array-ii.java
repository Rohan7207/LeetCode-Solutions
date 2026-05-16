// Problem: Find Minimum in Rotated Sorted Array II
// Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/?envType=daily-question&envId=2026-05-16
// Difficulty: Hard

// Approach:
// Use Binary Search to find the minimum element
// in the rotated sorted array containing duplicates.
// Compare mid element with high element:
//     - If nums[mid] > nums[h],
//       minimum lies in right half.
//     - If nums[mid] < nums[h],
//       minimum lies in left half including mid.
//     - If nums[mid] == nums[h],
//       duplicates prevent identifying the sorted half,
//       so reduce search space by decrementing h.
// Continue until low and high meet.
// Return nums[l] as minimum element.

// Time Complexity:
//     Average: O(log n)
//     Worst Case: O(n)
// Space Complexity: O(1)


class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int h = nums.length - 1;

        while (l < h) {
            int mid = l + (h - l) / 2;
            if (nums[mid] > nums[h]) {
                l = mid + 1;
            } else if (nums[mid] < nums[h]) {
                h = mid;
            } else h--;
        }

        return nums[l];
    }
}
