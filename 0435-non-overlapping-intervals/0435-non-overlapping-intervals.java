// Problem: Non -overlapping Intervals
// Link: https://leetcode.com/problems/non-overlapping-intervals/
// Difficulty: Medium

// Approach:
// Sort intervals according to their
// ending times.
// Keep track of the end time of the
// last selected non-overlapping interval
// using 'prev'.
// Traverse remaining intervals.
// If:
//     prev > currentStart
// then current interval overlaps with
// the previously selected interval.
//
// To minimize removals, keep the interval
// with the smaller ending time
// (already ensured by sorting).
//
// Therefore:
//     remove current interval
//     count++
// Otherwise:
//     no overlap
//     keep current interval
//     update prev to current end
// Finally return count.

// Time Complexity: O(n log n)
// Space Complexity: O(1)


class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int prev = intervals[0][1];
        int count = 0;

        for (int i = 1; i < intervals.length; i++) {
            if (prev > intervals[i][0]) {
                count++;
            } else {
                prev = intervals[i][1];
            }
        }

        return count;
    }
}
