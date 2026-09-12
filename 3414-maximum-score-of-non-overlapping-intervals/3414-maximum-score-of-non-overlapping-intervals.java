// Problem: Maximum Score of Non-overlapping intervals
// Link: https://leetcode.com/problems/maximum-score-of-non-overlapping-intervals/?envType=daily-question&envId=2026-09-12
// Difficulty: Hard

// Approach:
// Use Sort + Binary Search + Bottom-Up DP.
//
// 1. Store each interval's original index before sorting.
//
// 2. Sort intervals by their start time.
//
// 3. For every interval, precompute the first interval whose start
//    is strictly greater than the current interval's end using binary search.
//
// 4. Define dp[i][k] as the best result starting from interval i
//    when we can still choose at most k intervals.
//
// 5. For every state, consider two choices:
//      - Skip current interval → dp[i + 1][k]
//      - Take current interval → weight[i] + dp[nextIdx[i]][k - 1]
//
// 6. Choose the result with the larger total weight.
//
// 7. If weights are equal, choose the lexicographically smaller
//    sorted list of original indices.
//
// 8. Initialize dp[i][0] and dp[n][k] as empty selections because
//    these represent the recursion base cases.
//
// 9. The answer is dp[0][4] because at most 4 intervals can be selected.

// Time Complexity: O(n log n + 4n log n)
// Space Complexity: O(n * 4)


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

        dp = new Pair[n + 1][5];

        // Base case: k == 0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = new Pair(0, new ArrayList<>());
        }

        // Base case: i>= n
        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new Pair(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {
            long weight = intervals.get(i).get(2);
            int originalIndex = intervals.get(i).get(3);

            for (int k = 1; k <= 4; k++) {
                // Skip current interval
                Pair skip = dp[i + 1][k];

                // Take current interval
                Pair next = dp[nextIdx[i]][k - 1];

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

                dp[i][k] = result;
            }
        }

        Pair result = dp[0][4];
        int[] ans = new int[result.indexes.size()];

        for (int i = 0; i < result.indexes.size(); i++) {
            ans[i] = result.indexes.get(i);
        }

        return ans;
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
