// Problem: Most Visited Sector in a Circular Track
// Link: https://leetcode.com/problems/most-visited-sector-in-a-circular-track/
// Difficulty: Easy

// Approach:
// 1. Identify the starting sector: rounds[0].
// 2. Identify the ending sector: rounds[m - 1].
//
// 3. The most visited sectors are determined only by the
//    starting and ending sectors because every complete
//    circular round visits all sectors equally.
//
// 4. If start <= end:
//      Add all sectors from start to end.
//
// 5. Otherwise, the path wraps around from n back to 1:
//      a. Add sectors from 1 to end.
//      b. Add sectors from start to n.
//
// 6. Return the resulting list.

// Time Complexity: O(n)
// Space Complexity: O(n) for the result list.


class Solution {
    public List<Integer> mostVisited(int n, int[] rounds) {
        int m = rounds.length;
        int start = rounds[0];
        int end = rounds[m - 1];
        List<Integer> res = new ArrayList<>();

        if (start <= end) {
            for (int i = start; i <= end; i++) {
                res.add(i);
            }
        } else {
            for (int i = 1; i <= end; i++) {
                res.add(i);
            }

            for (int i = start; i <= n; i++) {
                res.add(i);
            }
        }

        return res;
    }
}
