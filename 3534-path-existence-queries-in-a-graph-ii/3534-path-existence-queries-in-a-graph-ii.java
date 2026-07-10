// Problem: Path Existence Queries in a Graph II
// Link: https://leetcode.com/problems/path-existence-queries-in-a-graph-ii/?envType=daily-question&envId=2026-07-10
// Difficulty: Hard

// Approach:
// Since edges depend on node values (not their original indices),
// first pair every value with its original index and sort them.
//
// Build a position[] array.
//     position[originalIndex] = sorted position.
// This helps convert every query into sorted positions.
//
// Scan the sorted array once to build connected components.
//     • Start with component 0.
//     • If the difference between two consecutive values is
//       greater than maxDiff,
//       start a new component.
//     • Otherwise,
//       both positions belong to the same component.
// Nodes in different components can never have a path.
//
// Build next[] using Two Pointers.
//     next[i] = farthest sorted position reachable from i
//               in one edge.
// The right pointer never moves backward,
// so this preprocessing takes O(n).
//
// Build the Binary Lifting table.
//     jump[i][0] = next[i]
//     jump[i][k] = jump[jump[i][k-1]][k-1]
//
// Here,
// jump[i][k] represents the position reached after
// making 2^k greedy jumps from position i.
//
// For every query:
//     • Convert both original indices into sorted positions.
//     • Ensure left <= right.
//     • If they belong to different components,
//       return -1.
//     • Otherwise,
//       use Binary Lifting.
//         - Start from the largest power of two.
//         - If taking 2^k jumps still keeps us
//           before the destination,
//           take those jumps.
//         - Add 2^k to the answer.
//     • After the loop,
//       one final jump reaches the destination.

// Time Complexity:
//     Sorting                 : O(n log n)
//     Component Building      : O(n)
//     next[] (Two Pointers)   : O(n)
//     Binary Lifting Build    : O(n log n)
//     Each Query              : O(log n)
//     Total                   : O(n log n + q log n)
//
// Space Complexity: O(n log n)


class Solution {
    private int LOG = 20;

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int len = nums.length;
        int[][] arr = new int[len][2];

        // Store value with original index
        for (int i = 0; i < len; i++) {
            arr[i][0] = nums[i]; // Value
            arr[i][1] = i; // Original Index
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        // Map original index → sorted position
        int[] position = new int[len];
        for (int i = 0; i < len; i++) {
            position[arr[i][1]] = i;
        }

        int[] component = new int[len];
        int comp = 0;
        component[0] = 0;
        for (int i = 1; i < len; i++) {
            if (arr[i][0] - arr[i - 1][0] > maxDiff)
                comp++;

            component[i] = comp;
        }

        // Build next[] with two pointers
        int[] next = new int[len];
        int j = 0;
        for (int i = 0; i < len; i++) {
            j = Math.max(j, i);

            while (j + 1 < len && arr[j + 1][0] - arr[i][0] <= maxDiff) {
                j++;
            }

            next[i] = j;
        }

        int[][] jump = new int[len][LOG];

        // Fill first column
        for (int i = 0; i < len; i++) {
            jump[i][0] = next[i];
        }

        // Fill remaining columns
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < len; i++) {
                jump[i][k] = jump[jump[i][k - 1]][k - 1];
            }
        }

        int qLen = queries.length;
        int[] ans = new int[qLen];

        for (int i = 0; i < qLen; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            int left = position[u];
            int right = position[v];

            if (left > right) {
                int temp = left;
                left = right;
                right = temp;
            }

            if (component[left] != component[right]) {
                ans[i] = -1;
            } else {
                ans[i] = minJump(left, right, jump);
            }
        }

        return ans;
    }

    private int minJump(int left, int right, int[][] jump) {
        if (left == right)
            return 0;

        int answer = 0;

        for (int k = LOG - 1; k >= 0; k--) {
            if (jump[left][k] < right) {
                left = jump[left][k];

                answer += 1 << k;
            }
        }

        return answer + 1;
    }
}
