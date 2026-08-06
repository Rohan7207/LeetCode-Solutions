// Problem: Find the Town Judge
// Link: https://leetcode.com/problems/find-the-town-judge/
// Difficulty: Easy

// Approach:
// Treat the trust relationships as a directed graph where
// an edge a → b means person a trusts person b.
//
// Maintain two arrays:
//
// 1. inDegree[i]  = number of people who trust person i.
// 2. outDegree[i] = number of people person i trusts.
//
// Traverse every trust relationship:
//
//      outDegree[a]++
//      inDegree[b]++
//
// After processing all trust pairs, iterate through every
// person from 1 to n.
//
// A person can be the town judge only if:
//
// 1. They trust nobody.
//      outDegree[i] == 0
//
// 2. Everyone else trusts them.
//      inDegree[i] == n - 1
//
// If such a person exists, return their label.
//
// If no one satisfies both conditions, return -1.

// Time Complexity:
// O(n + m)
// where m = trust.length
//
// Space Complexity:
// O(n)


class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] inDegree = new int[n + 1];
        int[] outDegree = new int[n + 1];

        for (int[] t : trust) {
            outDegree[t[0]]++;
            inDegree[t[1]]++;
        }

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == n - 1 && outDegree[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
