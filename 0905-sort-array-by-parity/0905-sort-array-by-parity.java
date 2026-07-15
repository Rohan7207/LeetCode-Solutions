// Problem: Sort Array By Parity
// Link: https://leetcode.com/problems/sort-array-by-parity/
// Difficulty: Easy

// Approach:
// Use the Two Pointers technique to partition the array
// into even and odd numbers in-place.
// Maintain two pointers:
//     left  -> starts from the beginning.
//     right -> starts from the end.
// Move the left pointer forward while it points to an
// even number because it is already in the correct position.
// Move the right pointer backward while it points to an
// odd number because it is already in the correct position.
// After these movements:
//     nums[left]  is an odd number (misplaced).
//     nums[right] is an even number (misplaced).
// Swap these two elements so they move to their
// correct partitions.
// Repeat this process until the two pointers meet.
// Since each element is visited at most once,
// the algorithm runs efficiently in linear time.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            while (left < right && nums[left] % 2 == 0) {
                left++;
            }

            while (left < right && nums[right] % 2 == 1) {
                right--;
            }

            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;

                left++;
                right--;
            }
        }

        return nums;
    }
}
