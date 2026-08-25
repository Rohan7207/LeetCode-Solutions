// Problem: Airplane Seat Assignment Probability
// Link: https://leetcode.com/problems/airplane-seat-assignment-probability/
// Difficulty: Medium

// Approach:
// Use mathematical observation instead of simulation or DP.
//
// 1. If n == 1:
//      The first passenger has only one seat, so they definitely
//      get their own seat.
//
//      answer = 1.0
//
// 2. If n >= 2:
//      The first passenger randomly chooses a seat.
//
//      There are two important final possibilities:
//
//      - The chain eventually takes seat 1.
//        Then passenger n gets their own seat.
//
//      - The chain eventually takes seat n.
//        Then passenger n cannot get their own seat.
//
//      These two outcomes have equal probability.
//
//      Therefore:
//          answer = 0.5
//
// 3. So the result is:
//
//      n == 1 → 1.0
//      n > 1  → 0.5

// Time Complexity: O(1)
// Space Complexity: O(1)


class Solution {
    public double nthPersonGetsNthSeat(int n) {
        if (n == 1) {
            return 1.0;
        }

        return 0.5;
    }
}
