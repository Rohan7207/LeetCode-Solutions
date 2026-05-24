// Problem: Rectangular Area
// Link: https://leetcode.com/problems/rectangle-area/
// Difficulty: Medium

// Approach:
// Calculate area of both rectangles separately.
//     area1 = width1 * height1
//     area2 = width2 * height2
// Find the overlapping region:
//     - Overlapping width is determined by
//       the minimum of right boundaries
//       minus the maximum of left boundaries.
//     - Overlapping height is determined by
//       the minimum of top boundaries
//       minus the maximum of bottom boundaries.
// If overlap width or height becomes negative,
// there is no overlap, so use 0.
// Compute overlap area: overlapArea = overlapWidth * overlapHeight
// Total covered area: area1 + area2 - overlapArea
// Return the result.

// Time Complexity: O(1)
// Space Complexity: O(1)


class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);

        int overlapArea = Math.max(0, Math.min(ax2, bx2) - Math.max(ax1, bx1)) *
                            Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));

        return area1 + area2 - overlapArea;
    }
}
