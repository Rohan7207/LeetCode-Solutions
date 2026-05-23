// Problem : Check if Array is Sorted and Rotated
// Link : https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/?envType=daily-question&envId=2026-05-23
// Difficulty : Easy

// Approach:
// Traverse the array and count the number
// of places where the current element is
// greater than the next element.
//
// Use circular comparison by checking:
//     nums[i] and nums[(i + 1) % n]
//
// A sorted and rotated array can have
// at most one such decreasing point
// (rotation break).
//
// If more than one break exists,
// the array cannot be obtained by rotating a sorted array.
// Return true if breaks <= 1, otherwise return false.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean check(int[] nums) {
        int breaks = 0;
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            if(nums[i] > nums[(i + 1) % n]) {
                breaks++;
            }
        }

        return breaks <= 1;
    }
}
