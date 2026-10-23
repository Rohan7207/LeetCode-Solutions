// Problem: Count Odd Numbers in an Interval Range
// Link: https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/
// Difficulty: Easy

// Approach:
//
// 1. Calculate the total number of integers in the inclusive
//    range [low, high] using:
//
//       range = high - low + 1
//
// 2. Every two consecutive integers contain exactly one odd
//    and one even number.
//
// 3. If range is even, exactly half of the numbers are odd.
//    Return range / 2.
//
// 4. If range is odd, one parity occurs once more than the other.
//    Check whether both low and high are odd.
//
// 5. If both boundaries are odd, the range contains one extra
//    odd number. Return range / 2 + 1.
//
// 6. Otherwise, return range / 2.

// Time Complexity: O(1)
// Space Complexity: O(1)


class Solution {
    public int countOdds(int low, int high) {
        int range = high - low + 1;

        if (range % 2 == 0) {
            return range / 2;
        }

        if (low % 2 == 1 && high % 2 == 1) {
            return range / 2 + 1;
        }

        return range / 2;
    }
}
