// Problem: Minimum Operations to Sort a Permutation
// Link: https://leetcode.com/problems/minimum-operations-to-sort-a-permutation/?envType=daily-question&envId=2026-10-22
// Difficulty: Medium

// Approach:
//
// 1. Traverse the array and check whether consecutive elements
//    follow circular ascending or circular descending order.
//
// 2. `ascCount` counts adjacent pairs satisfying:
//       nums[i] == (nums[i - 1] + 1) % n
//
// 3. `dscCount` counts adjacent pairs satisfying:
//       nums[i - 1] == (nums[i] + 1) % n
//
// 4. If all adjacent pairs are ascending and nums[0] == 0,
//    the array is already sorted, so return 0.
//
// 5. If all adjacent pairs are ascending but the array starts
//    somewhere other than 0, calculate the rotations needed
//    to reach the sorted order in either direction and return
//    the smaller cost.
//
// 6. If all adjacent pairs are descending, the array needs
//    one reversal. Calculate the rotation costs around that
//    reversal and return the smaller total.
//
// 7. If neither condition holds, the array does not follow
//    a consistent circular ascending or descending order,
//    so return -1.

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
