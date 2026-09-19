// Problem: Circle and Rectangle Overlapping
// Link: https://leetcode.com/problems/circle-and-rectangle-overlapping/?envType=daily-question&envId=2026-09-19
// Difficulty: Medium

// Approach:
// Use Closest Point on Rectangle + Distance Check.
//
// 1. Find the closest x-coordinate on the rectangle to the circle's center.
//    - If the center is left of the rectangle, choose x1.
//    - If the center is right of the rectangle, choose x2.
//    - Otherwise, choose xCenter.
//
// 2. Apply the same logic to find the closest y-coordinate.
//
// 3. The resulting point (x0, y0) is the closest point on the rectangle.
//
// 4. Calculate the squared distance between the circle's center and
//    the closest point.
//
// 5. If the distance is <= radius, the circle overlaps the rectangle.

// Time Complexity: O(1)
// Space Complexity: O(1)


class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int x0, y0;

        if (x1 > xCenter) {
            x0 = x1;
        } else if (x2 < xCenter) {
            x0 = x2;
        } else {
            x0 = xCenter;
        }

        if (y1 > yCenter) {
            y0 = y1;
        } else if (y2 < yCenter) {
            y0 = y2;
        } else {
            y0 = yCenter;
        }

        // Calculate distance: (x0, y0) -------- (xCenter, yCenter)
        return Math.sqrt(((x0 - xCenter) * (x0 - xCenter)) + ((y0 - yCenter) * (y0 - yCenter))) <= radius;
    }
}
