class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // 1. Sort: Ascending by start, Descending by end if starts are equal
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? Integer.compare(b[1], a[1]) : Integer.compare(a[0], b[0]));
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