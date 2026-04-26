// Problem: First Missing Positive
// Link: https://leetcode.com/problems/first-missing-positive/
// Difficulty: Hard

// Approach:
// Place each positive number at its correct index.
// For a number x, its correct position is index x - 1.
// Traverse the array:
//     - If the current number is in the range [1, n]
//       and is not already placed correctly,
//       swap it with the element at its correct index.
// After rearranging the array, traverse it again:
//     - The first index where nums[i] != i + 1
//       gives the first missing positive number.
// If all positions are correct, return n + 1.

// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int firstMissingPositive(int[] nums) {
        // O(n) and O(1) = index placement / cyclic sort idea

        int i = 0;

        while(i < nums.length) {
            int correct = nums[i] - 1;

            if(nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[correct]) {
                int temp = nums[i];
                nums[i] = nums[correct];
                nums[correct] = temp;
            } else {
                i++;
            }
        }

        for(i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }
}
