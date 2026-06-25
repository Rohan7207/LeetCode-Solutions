// Problem: Largest Triangle Area
// Link: https://leetcode.com/problems/largest-triangle-area/
// Difficulty: Easy

// Approach:
// The goal is to find the maximum area triangle that can be formed
// using any three points.
// Observation:
// A triangle is formed by choosing any 3 different points from the given
// set of points.
// So we try every possible combination of three points using three loops.
// For every combination:
// 1. Select three points:
//        p1 -> points[i]
//        p2 -> points[j]
//        p3 -> points[k]
// 2. Extract their x and y coordinates.
// 3. Calculate the triangle area using the Shoelace formula:
//        Area = 0.5 * | x1(y2-y3) + x2(y3-y1) + x3(y1-y2) |
// 4. Compare this area with the current maximum area.
// 5. Return the maximum found area.
// Key Observation:
// Since n is small, checking all possible triples is efficient enough.

// Time Complexity:
// Three nested loops are used:
// O(n³)
//
// Space Complexity:
// O(1)


class Solution {
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double maxArea = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int x1 = points[i][0];
                    int y1 = points[i][1];

                    int x2 = points[j][0];
                    int y2 = points[j][1];

                    int x3 = points[k][0];
                    int y3 = points[k][1];

                    double area = 0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));

                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }
}
