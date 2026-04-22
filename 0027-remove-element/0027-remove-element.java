// Problem: Remove Element
// Link: https://leetcode.com/problems/remove-element/
// Difficulty: Easy

// Approach:
// Use a pointer k to track the position of the next unique element.
// Traverse the array from left to right.
// If the current element is different from the last unique element,
// place it at index k and increment k.
// Return k as the count of unique elements.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != val) {
                nums[k++] = nums[i];
            }
        }
        
        return k;
    }
}
