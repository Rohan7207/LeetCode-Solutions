// Problem: Find Pivot Index
// Link: https://leetcode.com/problems/find-pivot-index/
// Difficulty: Easy

// Approach:
// We need to find an index where:
//     sum of elements on left side
//     ==
//     sum of elements on right side
// First calculate totalSum of the whole array.
// Then traverse the array while maintaining leftSum.
// For index i:
//     rightSum = totalSum - leftSum - nums[i]
// Why?
//     totalSum contains:
//         left side + nums[i] + right side
//     So:
//         right side = totalSum - leftSum - nums[i]
// If:
//     leftSum == rightSum
// then index i is the pivot index.
// Return i.
// After checking current index,
// add nums[i] to leftSum because now it becomes
// part of the left side for the next index.
// If no pivot index is found, return -1.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int pivotIndex(int[] nums) {
        int leftSum = 0;
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        int rightSum = 0;
        for (int i = 0; i < nums.length; i++) {
            rightSum = totalSum - leftSum - nums[i];

            if (leftSum == rightSum) {
                return i;
            }

            leftSum += nums[i];
        }

        return -1;
    }
}
