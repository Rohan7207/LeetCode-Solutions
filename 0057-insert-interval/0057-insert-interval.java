// Problem: Insert Interval
// Link: https://leetcode.com/problems/insert-interval/
// Difficulty: Medium

// Approach:
// Traverse the intervals and insert the new interval
// in the correct position.
// First, add all intervals that end before the new interval starts.
// Then merge all overlapping intervals by updating
// the start and end of the new interval.
// Add the merged interval to the result.
// Finally, add all remaining intervals.
// Convert the result list into a 2D array and return it.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //Solution 2 with O(n)

        int n = intervals.length;
        List<int[]> res = new ArrayList<>();

        int i = 0;
        //Add all interval before new interval
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }

        //Merge overrlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        res.add(newInterval); //Add new interval at current position

        //Add all intervals after merged interval
        while (i < n) {
            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }
}
