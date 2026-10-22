// Problem: Minimum Operations to Sort a Permutation
// Link: https://leetcode.com/problems/minimum-operations-to-sort-a-permutation/?envType=daily-question&envId=2026-10-22
// Difficulty: Medium

// Approach:
//
// 1. Traverse the array and count adjacent pairs that follow
//    circular ascending and circular descending order.
//
// 2. `ascCount` counts pairs satisfying:
//       nums[i] == (nums[i - 1] + 1) % n
//
// 3. `dscCount` counts pairs satisfying:
//       nums[i - 1] == (nums[i] + 1) % n
//
// 4. If every adjacent pair is ascending and nums[0] == 0,
//    the array is already sorted. Return 0.
//
// 5. If every adjacent pair is ascending but the array starts
//    at a different position, calculate the cost of sorting it
//    using left rotations or right rotations.
//
//    A right rotation is simulated using:
//       Reverse + Left Rotation + Reverse
//
//    Therefore, its cost is:
//       1 + required left rotations + 1
//
// 6. If every adjacent pair is descending, one reversal is
//    mandatory. Calculate the total cost for each possible
//    direction, including the reversal operation, and return
//    the minimum.
//
// 7. If neither ascending nor descending circular order holds,
//    return -1.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        int ascCount = 0;
        int dscCount = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] == (nums[i - 1] + 1) % n) {
                ascCount++;
            }

            if (nums[i - 1] == (nums[i] + 1) % n) {
                dscCount++;
            }
        }

        // If in ascending nature and if already sorted then no steps requried
        if (ascCount == n - 1 && nums[0] == 0) {
            return 0;
        }

        if (ascCount == n - 1) {
            int leftRot = n - nums[0];
            int rightRot = 1 + (nums[n - 1] + 1) + 1;

            return Math.min(leftRot, rightRot);
        }

        if (dscCount == n - 1) {
            // Mandatory one reverse
            int leftRot = n - nums[n - 1] + 1;
            int rightRot = ((nums[0] + 1) % n) + 1;

            return Math.min(leftRot, rightRot);
        }
        
        return -1; // Bad order
    }
}
