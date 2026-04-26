// Problem: First Missing Positive
// Link: https://leetcode.com/problems/first-missing-positive/
// Difficulty: Hard

// Approach:
// Use a boolean array to track positive numbers present in the array.
// Traverse the nums array:
//     - If a number is in the range [1, nums.length],
//       mark its corresponding index as true in the boolean array.
// Traverse from 1 to nums.length:
//     - The first index not marked true is the missing positive number.
// If all numbers from 1 to nums.length are present,
// return nums.length + 1.

// Time Complexity: O(n)
// Space Complexity: O(n)

class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean[] found = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0 && nums[i] <= n) {
                found[nums[i]] = true; 
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!found[i]) {
                return i; 
            }
        }

        return n + 1; 
    }
}
