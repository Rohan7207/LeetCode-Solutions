// Problem: Remove Covered Intervals
// Link: https://leetcode.com/problems/remove-covered-intervals/?envType=daily-question&envId=2026-07-06
// Difficulty: Medium

// Approach:
// An interval [a, b] is covered if there exists another interval
// [c, d] such that:
//      c <= a
//      AND
//      b <= d
// First, sort the intervals:
//     • Ascending by start.
//     • If two intervals have the same start,
//       place the longer interval first
//       (descending by end).
// This guarantees that if two intervals start at the
// same position, the larger interval appears before
// the smaller one.
// Traverse the sorted intervals while maintaining
// the maximum ending point seen so far.
// For every interval:
//     • If its end is greater than maxEnd,
//       it is not covered.
//       Count it and update maxEnd.
//     • Otherwise,
//       its end is less than or equal to maxEnd,
//       meaning a previous interval already covers it.
// Return the count of remaining intervals.

// Time Complexity: O(n log n)
// Space Complexity: O(1)
// (Ignoring the space used by sorting.)


class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int remainingCount = 0;
        int maxEnd = 0;

        // [1, 4] [2, 8] [3, 6] 
        for (int[] interval : intervals) {
            if (interval[1] > maxEnd) {
                remainingCount++;
                maxEnd = interval[1];
            }
        }

        return remainingCount;
    }
}
