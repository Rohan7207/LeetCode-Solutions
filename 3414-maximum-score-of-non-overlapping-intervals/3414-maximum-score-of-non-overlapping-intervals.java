class Solution {

    class Pair {
        long score;
        List<Integer> indexes;

        Pair(long score, List<Integer> indexes) {
            this.score = score;
            this.indexes = indexes;
        }
    }

    int n;
    int[] nextIdx;
    Pair[][] dp;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        n = intervals.size();

        // Add original index
        for (int i = 0; i < n; i++) {
            intervals.get(i).add(i);
        }

        // Sort by start time
        intervals.sort((a, b) -> {
            if (!a.get(0).equals(b.get(0))) {
                return Integer.compare(a.get(0), b.get(0));
            }

            return Integer.compare(a.get(1), b.get(1));
        });

        // Precompute next non-overlapping interval
        nextIdx = new int[n];

        for (int i = 0; i < n; i++) {
            nextIdx[i] = findNext(intervals, intervals.get(i).get(1));
        }

        dp = new Pair[n][5];

        Pair result = solve(intervals, 0, 4);

        int[] ans = new int[result.indexes.size()];

        for (int i = 0; i < result.indexes.size(); i++) {
            ans[i] = result.indexes.get(i);
        }

        return ans;
    }

    private Pair solve(List<List<Integer>> intervals, int i, int k) {

        if (i >= n || k == 0) {
            return new Pair(0, new ArrayList<>());
        }

        if (dp[i][k] != null) {
            return dp[i][k];
        }

        // Skip current interval
        Pair skip = solve(intervals, i + 1, k);

        // Take current interval
        Pair next = solve(intervals, nextIdx[i], k - 1);

        long weight = intervals.get(i).get(2);
        int originalIndex = intervals.get(i).get(3);

        List<Integer> takeIndexes = new ArrayList<>(next.indexes);
        takeIndexes.add(originalIndex);

        // Keep indexes sorted for lexicographical comparison
        Collections.sort(takeIndexes);

        Pair take = new Pair(
                weight + next.score,
                takeIndexes);

        Pair result;

        if (take.score > skip.score) {
            result = take;
        } else if (take.score < skip.score) {
            result = skip;
        } else {
            // Same weight -> lexicographically smaller indexes
            if (compare(take.indexes, skip.indexes) < 0) {
                result = take;
            } else {
                result = skip;
            }
        }

        return dp[i][k] = result;
    }

    private int findNext(List<List<Integer>> intervals, int endPoint) {

        int low = 0;
        int high = n - 1;
        int res = n;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            // Strictly non-overlapping
            if (intervals.get(mid).get(0) > endPoint) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return res;
    }

    private int compare(List<Integer> a, List<Integer> b) {

        int size = Math.min(a.size(), b.size());

        for (int i = 0; i < size; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}

/*
    intervals = [[1,3,2],[4,5,2],[1,5,5],[6,9,3],[6,7,1],[8,9,1]]
    Consider we take interval 0, now we can take or not take next interval, and if we are taking next inteval then we must take non-overlapping line endCurr > startNext. But that leads to if we take current i as interval then we must traverse all intervals to take or not take instead of that we can sort based on starting point that gives us : 

    [[1, 3, 2], [1, 5, 5], [4, 5, 2], [6, 7, 1], [6, 9, 3], [8, 9, 1]]
    So now if we take first interval and if we want to next interval we should search interval such that:
    currEnd < nextStart, which can be done using binary search:
        nextInterval = currEnd < nextStart
    - Since we are doing sorting and we must return the indexes as answer so we must store original index in their interval only like :
        intervals = [[1,3,2,0],[4,5,2,1],[1,5,5,2],[6,9,3,3],[6,7,1,4],[8,9,1,5]]

    Steps :
     1. Intervals : store original indexes
     2. Sort Intervals : sort(begin(intervals), end(intervals))
     3. Preprocess nextInterval(nextIdx) for each interval (so that we not use bs repeatedly, just use once)
     4. Solve(intervals, 0, k);

        Binary Search:
        int[] nextIdx = new int[n];
        for(int i = 0; i < n; i++) { O(nlogn)
            int endPoint = intervals[i][1];
            nextIdx[i] = findNext(intervals, endPoint) (logn)
        }

        int findNext(intervals, endPoint) {
            int low = 0, high = n - 1;
            int res = n;

            while(low <= high) {
                mid = low + (high - low) / 2;

                if(intervals[mid][0] > endPoint) {
                    res = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return res;
        }

    // Bcz at recursion we need to return two type of info maxScore and index which have been chosen
    
    class Pair {
        int score;  // weight
        List<Integer> indexes;
    }

    Memoization : List<List<Pair>> t;
                 t[n + 1][k + 1];

    Recursion :
        Pair solve(intervals, i, k) {
            if(k == 0 || i >= n) {
                return Pair();
            }

            Pair skip = solve(intervals, i + 1, k);

            Pair temp = solve(intervals, nextIdx[i], k - 1);
            int wt = intervals[i][2];
            int idx = intervals[i][3];
            int j = nextIdx[i];  // O(1)

            Pair take;
            take.score = intervals[i][2] + temp.score;
            take.indexes.add(temp.indexes);
            take.indexes.add(idx)
            sort (begin(take.indexes), end(take.indexes))

            Node res;
            if(skip.score > take.score) {
                res = skip;
            } else if(skip.score < take.score) {
                res = take;
            } else {
                res = (skip.indexes < take.indexes) ? skip : take;
            }

            return res;
        }
*/