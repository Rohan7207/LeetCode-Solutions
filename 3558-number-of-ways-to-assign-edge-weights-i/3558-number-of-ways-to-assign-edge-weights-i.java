// Problem: Number of Ways to Assign Edge Weights I
// Link: https://leetcode.com/problems/number-of-ways-to-assign-edge-weights-i/?envType=daily-question&envId=2026-06-11
// Difficulty: Medium

// Approach:
// The tree is given as a 2D edges array,
// so first convert it into an adjacency list.
// Since the tree is rooted at node 1,
// start DFS from node 1.
// During DFS:
//     - Track current depth
//     - Update maximum depth found so far
//     - Use parent to avoid going back
//       to the previous node
// After DFS:
//     maxDepth = number of edges from node 1
//                to the deepest node
// Now we need to assign weights 1 or 2
// to the edges on that deepest path
// such that the total sum is odd.
// If deepest path has d edges:
//     Total assignments = 2^d
// Exactly half of them have odd sum.
// Therefore:
//     Odd sum assignments = 2^(d - 1)
// So answer:
//     power(2, maxDepth - 1)
// Use fast exponentiation because answer can be large
// and modulo 1e9+7 is required.

// Time Complexity: O(n + log(maxDepth))
// Space Complexity: O(n)


class Solution {
    int maxDepth = 0;
    int MOD = 1000000007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dfs(graph, 1, -1, 0);

        return power(2, maxDepth - 1);
    }

    private void dfs(List<List<Integer>> graph, int node, int parent, int depth) {
        maxDepth = Math.max(maxDepth, depth);
        
        for (int child : graph.get(node)) {
            if (parent == child) continue;

            dfs(graph, child, node, depth + 1);
        }
    }

    private int power(long base, int exp) {
        long res = 1;

        while (exp > 0) {
            if (exp % 2 == 1) {
                res = (res * base) % MOD;
            }

            base = (base * base) % MOD;
            exp /= 2;
        }

        return (int) res;
    }
}
