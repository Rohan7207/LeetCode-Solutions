class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> res = new LinkedList<>();

        for (int[] interval : intervals) {
            if (res.isEmpty() || res.getLast()[1] < interval[0]) {
                res.add(interval);
            } else {
                res.getLast()[1] = Math.max(res.getLast()[1], interval[1]);
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}

/*
We sort the given input and check for 2nd list 1st element is lesser than previous 2nd
element then merge that two intervals based on min and max
Consider [[2,5],[4,6],[7,9],[11,15],[13,16]] that is after sorting
1. since 4<5, there is overlap so min of 2 and 4 and max of 5 and 6 i.e. is 2 6 is added to res
2. 7>6, there is no overlap
3. 11>9, same here
4. 13<15, there is overlap gives 11 16 added to res

Time = O(nlogn) and Space =  O(n) 
*/
