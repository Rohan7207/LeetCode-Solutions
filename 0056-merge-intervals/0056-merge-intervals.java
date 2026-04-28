// Problem: Insert Interval
// Link: https://leetcode.com/problems/insert-interval/
// Difficulty: Medium

// Approach:
// Sort the intervals based on starting values.
// If starting values are equal, sort by ending values.
// Use pointer l to track the last merged interval position.
// Traverse the intervals starting from index 1:
//     - If current interval does not overlap with
//       the last merged interval:
//           move l forward and store the current interval.
//     - Otherwise, merge intervals by updating
//       the ending value with the maximum end.
// Return only the merged portion of the array
// using Arrays.copyOfRange().

// Time Complexity: O(n log n)
// Space Complexity: O(1) (excluding sorting space)


class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

        int l = 0;

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[l][1] < intervals[i][0]) {
                intervals[++l][0] = intervals[i][0];
                intervals[l][1] = intervals[i][1];
            } else {
                intervals[l][1] = Math.max(intervals[l][1], intervals[i][1]);
            }
        }

        return Arrays.copyOfRange(intervals, 0, l + 1);
    }
}
