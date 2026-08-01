// Problem: Maximum Width Ramp
// Link: https://leetcode.com/problems/maximum-width-ramp/
// Difficulty: Medium

// Approach:
// A ramp is valid if:
//
//      i < j
//      nums[i] <= nums[j]
//
// To maximize the width (j - i), we want:
//
// - the smallest possible left index (i)
// - the largest possible right index (j)
//
// Step 1: Build a Monotonic Decreasing Stack
//
// Traverse the array from left to right.
//
// Store only indices whose values are strictly smaller than every
// value seen before.
//
// Why?
// If an earlier index has a smaller (or equal) value, any later
// larger value can never be a better left endpoint because the earlier
// index always gives a larger width.
//
// Thus, the stack stores only the best candidate left indices.
//
// Step 2: Traverse from Right to Left
//
// Start from the rightmost index because it gives the largest
// possible width.
//
// For every index j:
//
// - Compare nums[j] with the value at the stack's top.
// - If nums[j] >= nums[stack[top]], a valid ramp is found.
// - Compute:
//
//      width = j - stack[top]
//
// - Update the answer.
// - Pop that index because this is the farthest possible j it can pair
//   with, so it can never produce a larger width later.
//
// Continue popping while the current j satisfies the ramp condition.
//
// After processing all right indices, the maximum recorded width is
// the answer.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int[] stack = new int[n];
        int top = -1;

        for (int i = 0; i < n; i++) {
            if (top == -1 || nums[i] < nums[stack[top]]) {
                stack[++top] = i;
            }
        }

        int ans = 0;
        for (int j = n - 1; j >= 0; j--) {
            while (top >= 0 && nums[stack[top]] <= nums[j]) {
                ans = Math.max(ans, j - stack[top--]);
            }
        }

        return ans;
    }
}
