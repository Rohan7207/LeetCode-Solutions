// Problem: Jump Game
// Link: https://leetcode.com/problems/jump-game/
// Difficulty: Medium

// Approach:
// Use a greedy approach to determine whether the last index is reachable.
// Start with the last index as the target position.
// Traverse the array from right to left:
//     - If the current index plus its jump length
//       can reach or cross the target position,
//       update the target position to the current index.
// After traversal, if the target position becomes 0,
// then the first index can reach the last index.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean canJump(int[] nums) {
        int finall = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= finall) {
                finall = i;
            }
        }

        return finall == 0 ? true : false;
    }
}
