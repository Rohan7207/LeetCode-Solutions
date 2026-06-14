// Problem: Max Consecutive Ones
// Link: https://leetcode.com/problems/max-consecutive-ones/
// Difficulty: Easy

// Approach:
// We need to find the maximum number of
// consecutive 1's in the array.
// Traverse the array and whenever a 1 is found:
//     - Start counting consecutive 1's
//     - Continue moving forward while
//       elements are equal to 1
// Store the count in:
//     temp
// After the sequence ends:
//     - Update maxCount
// Continue scanning the remaining array.
// The outer loop finds the beginning of
// each sequence of 1's.
// The inner loop counts the length of
// that sequence.
// Return the largest count found.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            int temp = 0;
            while (i < n && nums[i] == 1) {
                temp++;
                i++;
            }

            maxCount = Math.max(maxCount, temp);
        }

        return maxCount;
    }
}
