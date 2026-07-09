// Problem: Path Existence Queries in a Graph
// Link: https://leetcode.com/problems/path-existence-queries-in-a-graph-i/?envType=daily-question&envId=2026-07-09
// Difficulty: Medium

// Approach:
// Building the graph explicitly is unnecessary because the array
// is already sorted.
// Observe:
//     If the difference between two adjacent numbers is
//     greater than maxDiff, then no edge can cross that gap.
// Therefore, that gap permanently splits the graph into
// two different connected components.
// Scan the array once and assign a component ID to every index.
//     • Start with component 0.
//     • For every adjacent pair:
//           nums[i] - nums[i-1]
//       If the difference is greater than maxDiff,
//       start a new component.
//       Otherwise,
//       the current index belongs to the same component
//       as the previous index.
// After preprocessing:
//     For every query (u, v):
//         If component[u] == component[v],
//         a path exists.
//         Otherwise,
//         they belong to different connected components,
//         so no path exists.

// Time Complexity:
//     Preprocessing : O(n)
//     Each Query    : O(1)
//     Total         : O(n + q)
//
// Space Complexity: O(n)


class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        boolean[] ans = new boolean[queries.length];
        int[] component = new int[nums.length];
        int currComponent = 0;
        component[0] = currComponent;

        for (int i = 1; i < nums.length; i++) {
            if (Math.abs(nums[i] - nums[i - 1]) > maxDiff) {
                currComponent++;
            }

            component[i] = currComponent;
        }

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            if (component[u] == component[v]) {
                ans[i] = true;
            } else {
                ans[i] = false;
            }
        }

        return ans;
    }
}
