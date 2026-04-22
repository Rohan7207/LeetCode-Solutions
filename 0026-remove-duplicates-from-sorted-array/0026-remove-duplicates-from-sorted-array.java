// Problem: Remove Duplicates from Sorted Array
// Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array/
// Difficulty: Easy

// Approach:
// Use two pointers to remove duplicates in-place.
// Keep one pointer to track the position of the next unique element.
// Traverse the array using another pointer.
// If the current element is different from the last unique element,
//     place it at the next unique position and move the pointer forward.
// Return the count of unique elements.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int removeDuplicates(int[] nums) {
        int j = 1;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != nums[j - 1]) {
                nums[j] = nums[i];
                j++;
            }
        }

        return j;
    }
}
