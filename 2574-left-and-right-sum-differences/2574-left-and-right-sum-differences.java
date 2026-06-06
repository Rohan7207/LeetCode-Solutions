// Problem : Left and Right Sum Differences
// Link : https://leetcode.com/problems/left-and-right-sum-differences/?envType=daily-question&envId=2026-06-06
// Difficulty : Easy

// Approach:
// First calculate total sum of the array.
// Maintain:
//     leftSum  = sum of elements before i
//     rightSum = sum of elements after i
// For each index:
//     rightSum = totalSum - leftSum - nums[i]
// because:
//     totalSum = leftSum + nums[i] + rightSum
// Store:
//     abs(leftSum - rightSum)
// in answer array.
// After processing current index,
// add nums[i] to leftSum so it becomes
// part of the left side for next index.

// Time Complexity: O(n)
// Space Complexity: O(1)
// (excluding output array)


class Solution {
    public int[] leftRightDifference(int[] nums) {
        int leftSum = 0;
        int rightSum = 0;
        int totalSum = 0;
        int[] ans = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            rightSum = totalSum - leftSum - nums[i];

            ans[i] = Math.abs(leftSum - rightSum);
            leftSum += nums[i];
        }

        return ans;
    }
}
