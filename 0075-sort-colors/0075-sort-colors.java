// Problem: Sort Colors
// Link: https://leetcode.com/problems/sort-colors/
// Difficulty: Medium

// Approach:
// Use the Dutch National Flag Algorithm
// to sort the array in one pass.
// Maintain three pointers:
//     - low  -> position for 0
//     - mid  -> current element
//     - high -> position for 2
// Traverse the array while mid <= high:
//     - If nums[mid] == 0:
//           swap nums[low] and nums[mid]
//           increment low and mid
//     - If nums[mid] == 1:
//           increment mid
//     - If nums[mid] == 2:
//           swap nums[mid] and nums[high]
//           decrement high
//           do not increment mid because
//           swapped value needs checking

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int mid = 0, low = 0, high = n - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low++] = nums[mid];
                nums[mid++] = temp;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                int temp = nums[high];
                nums[high--] = nums[mid];
                nums[mid] = temp;
            }
        }
    }
}
