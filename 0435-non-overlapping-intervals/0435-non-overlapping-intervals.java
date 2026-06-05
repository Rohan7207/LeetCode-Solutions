class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int prev = intervals[0][1];
        int count = 0;

        for (int i = 1; i < intervals.length; i++) {
            //Overlapping case
            if (prev > intervals[i][0]) {
                count++;
            } else {
                //Non overlapping assign prev to next interval
                prev = intervals[i][1];
            }
        }

        return count;
    }
}