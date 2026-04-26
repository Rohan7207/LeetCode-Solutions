// Problem: Trapping Rain Water
// Link: https://leetcode.com/problems/trapping-rain-water/
// Difficulty: Hard

// Approach:
// Use two pointers to calculate trapped rain water efficiently.
// Maintain two pointers at the leftmost and rightmost positions,
// along with leftMax and rightMax heights.
// Traverse while left pointer is less than right pointer:
//     - If left height is smaller than right height:
//         - Update leftMax if current height is greater.
//         - Otherwise, trapped water is leftMax - current height.
//         - Move left pointer forward.
//     - Otherwise:
//         - Update rightMax if current height is greater.
//         - Otherwise, trapped water is rightMax - current height.
//         - Move right pointer backward.
// Return the total trapped water.

// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while(left < right) {
            if(height[left] < height[right]) {
                if(height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }

                left++;
            } else {
                if(height[right] >= rightMax) {
                    rightMax = height[right]; 
                } else {
                    water += rightMax - height[right];
                }

                right--;
            }
        }

        return water;
    }
}
