// Problem: Rectangle Overlap
// Link: https://leetcode.com/problems/rectangle-overlap/?envType=daily-question&envId=2026-09-14
// Difficulty: Easy

// Approach:
// Use interval overlap on both axes.
//
// 1. Check whether the rectangles overlap horizontally (x-axis).
// 2. Check whether they overlap vertically (y-axis).
// 3. Both conditions must be true for the rectangles to have positive-area overlap.
// 4. Use strict `<` because touching at an edge or corner is not considered overlap.

// Time Complexity: O(1)
// Space Complexity: O(1)


class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return rec1[0] < rec2[2] && rec2[0] < rec1[2] 
            && rec1[1] < rec2[3] && rec2[1] < rec1[3];
    }
}
