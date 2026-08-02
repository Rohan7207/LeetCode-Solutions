// Problem: Powerful Integers
// Link: https://leetcode.com/problems/powerful-integers/
// Difficulty: Medium

// Approach:
// A powerful integer has the form:
//      x^i + y^j
// where i, j >= 0.
//
// Instead of iterating over all possible exponents, directly
// generate the values of x^i and y^j.
//
// Start with powerX = 1 (x^0).
//
// For every generated power of x:
// - Start powerY = 1 (y^0).
// - Generate every possible power of y.
// - Compute powerX + powerY.
// - If the sum is within the bound, add it to a HashSet.
//
// A HashSet is used because different pairs (i, j) may generate
// the same powerful integer, and duplicates should appear only once.
//
// Continue generating powers by repeatedly multiplying:
//      powerX *= x
//      powerY *= y
//
// Special Cases:
// If x == 1, then every power of x equals 1.
// Without stopping, powerX would never change, causing an infinite loop.
// Therefore, process powerX = 1 once and break.
//
// Similarly, if y == 1, process powerY = 1 once and break.
//
// Finally, convert the HashSet into a List and return it.

// Time Complexity:
// O(log_x(bound) × log_y(bound))
//
// Space Complexity:
// O(number of powerful integers)


class Solution {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<>();
        int powerX = 1;

        while (powerX <= bound) {
            int powerY = 1;

            while (powerY <= bound) {
                int sum = powerX + powerY;

                if (sum <= bound) {
                    set.add(sum);
                }

                if (y == 1) break;

                powerY *= y;
            }

            if (x == 1) break;

            powerX *= x;
        }

        return new ArrayList(set);
    }
}
