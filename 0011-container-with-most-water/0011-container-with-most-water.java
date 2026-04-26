// Problem: Container With Most Water
// Link: https://leetcode.com/problems/container-with-most-water/
// Difficulty: Medium

// Approach:
// Use two pointers starting from both ends of the array.
// In each iteration:
//     - Calculate the area using the distance between pointers
//       and the smaller of the two heights.
//     - Update the maximum area.
// Store the current minimum height as the limiting height.
// Move pointers inward while their heights are
// less than or equal to the current limiting height,
// since such heights cannot produce a larger area.
// Continue until both pointers meet.

// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int maxArea(int[] height) {
        // (width) × min(leftHeight, rightHeight)

        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while(left < right) {
            int width = right - left;
            int heightofLine = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, (heightofLine * width));

            while(left < right && height[left] <= heightofLine) left++;
            while(left < right && height[right] <= heightofLine) right--;
        }

        return maxArea;
    }
}
