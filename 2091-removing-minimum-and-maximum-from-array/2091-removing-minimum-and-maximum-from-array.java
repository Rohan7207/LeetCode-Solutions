// Problem: Removing Minimum and Maximum From Array
// Link: https://leetcode.com/problems/removing-minimum-and-maximum-from-array/?envType=daily-question&envId=2026-08-30
// Difficulty: Medium

// Approach:
// Use Greedy + Index Tracking.
//
// 1. Find the minimum and maximum values and store their indices.
//
// 2. Normalize their positions:
//      leftIndex = min(minIndex, maxIndex)
//      rightIndex = max(minIndex, maxIndex)
//
// 3. There are only three ways to remove both elements:
//
//      Case 1: Remove both from the front.
//      We must reach the element at rightIndex.
//      Deletions = rightIndex + 1
//
//      Case 2: Remove both from the back.
//      We must reach the element at leftIndex.
//      Deletions = n - leftIndex
//
//      Case 3: Remove one from each side.
//      Remove the left element from the front and
//      the right element from the back.
//      Deletions = (leftIndex + 1) + (n - rightIndex)
//
// 4. Take the minimum of these three possibilities.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int maxIndex = 0;
        int minIndex = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }

            if (nums[i] < min) {
                min = nums[i];
                minIndex = i;
            }
        }

        int leftIndex = Math.min(minIndex, maxIndex);
        int rightIndex = Math.max(maxIndex, minIndex);

        int front = rightIndex + 1;
        int back = n - leftIndex;

        int both = (leftIndex + 1) + (n - rightIndex);

        return Math.min(front, Math.min(back, both));
    }
}
