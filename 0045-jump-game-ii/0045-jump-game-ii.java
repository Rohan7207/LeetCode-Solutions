// Problem: Multiply Strings
// Link: https://leetcode.com/problems/multiply-strings/
// Difficulty: Medium

// Approach:
// Use a greedy approach to track the farthest reachable index.
// Traverse the array and maintain:
//     - currMax → farthest index reachable so far
//     - currEnd → end of the current jump range
// For each index:
//     - Update the farthest reachable position.
//     - If the current index reaches currEnd,
//       increment jump count and update currEnd to currMax.
// Continue until reaching the last index.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int jump(int[] nums) {

        int jump = 0, currMax = 0, currEnd = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            currMax = Math.max(currMax, i + nums[i]);

            if (i == currEnd) {
                jump++;
                currEnd = currMax;
            }
        }

        return jump;
    }
}
