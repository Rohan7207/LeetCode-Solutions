// Problem: Check If It Is a Straight Line
// Link: https://leetcode.com/problems/check-if-it-is-a-straight-line/
// Difficulty: Easy

// Approach:
// Use coordinate geometry + cross multiplication.
//
// 1. Take the first two points as the reference line.
//
//      (x1, y1)
//      (x2, y2)
//
// 2. For every remaining point (x3, y3), check whether it
//    has the same slope with the first point.
//
// 3. Normally, we could compare:
//
//      (y2 - y1) / (x2 - x1)
//          =
//      (y3 - y1) / (x3 - x1)
//
// 4. But division can cause:
//      - floating-point precision problems
//      - division by zero for vertical lines
//
// 5. So cross-multiply instead:
//
//      (y2-y1)(x3-x1) == (y3-y1)(x2-x1)
//
// 6. If any point fails this condition, the points are not
//    on the same straight line.
//
// 7. If every point satisfies it, return true.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int x1 = coordinates[0][0];
        int y1 = coordinates[0][1];

        int x2 = coordinates[1][0];
        int y2 = coordinates[1][1];

        for (int i = 2; i < coordinates.length; i++) {
            int x3 = coordinates[i][0];
            int y3 = coordinates[i][1];

            if (((y2 - y1) * (x3 - x1)) != ((y3 - y1) * (x2 - x1))) {
                return false;
            }
        }

        return true;
    }
}

/*
slope(first → second) = slope(first → current)

Instead of:

(y2-y1)/(x2-x1) = (y3-y1)/(x3-x1)

we cross-multiply:

(y2-y1)(x3-x1) = (y3-y1)(x2-x1)
*/
