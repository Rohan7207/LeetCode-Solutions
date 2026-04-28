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

        //Add all intervals after new interval
        while (i < n) {
            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }
}

/*
solution 1
    //O(nlogn) and O(n)
    This line appends newInterval to the list intervals. By doing this, we add the new interval into the list to handle it like the other intervals.
    This line sorts the intervals list in ascending order based on the starting times of each interval. Sorting allows us to process overlapping intervals in order, making it easier to merge them. 

    List<int[]> intervalList = new ArrayList<>(Arrays.asList(intervals));
    intervalList.add(newInterval);
    Collections.sort(intervalList, (a, b) -> Integer.compare(a[0], b[0]));

    List<int[]> res = new ArrayList<>();
    int[] current = intervalList.get(0);

    for(int i = 1; i < intervalList.size(); i++){
        int[] interval = intervalList.get(i);

        if(current[1] >= interval[0]){
            current[1] = Math.max(current[1], interval[1]);
        }else{
            res.add(current);
            current = interval;
        }
    }

    res.add(current);
    return res.toArray(new int[res.size()][]);
*/