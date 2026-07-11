// Problem: Is Graph Bipartite
// Link: https://leetcode.com/problems/is-graph-bipartite/
// Difficulty: Medium

// Approach:
// A graph is bipartite if we can divide its vertices into
// two sets such that every edge connects nodes from
// different sets.
// Instead of explicitly building two sets,
// think of it as a 2-coloring problem.
// Use a color array:
//     0 -> Uncolored
//     1 -> Color A
//     2 -> Color B
// Since the graph may be disconnected,
// iterate through every node.
//     • If the node is uncolored,
//       start a DFS from it and assign Color A.
// During DFS:
//     • Traverse every neighbor of the current node.
//     • If the neighbor is uncolored,
//       assign it the opposite color and continue DFS.
//     • If the neighbor already has the same color
//       as the current node,
//       the graph cannot be bipartite,
//       so return false.
// If every connected component is successfully colored
// without any conflicts,
// return true.

// Time Complexity:
//     DFS Traversal : O(V + E)
//
// Space Complexity:
//     Color Array   : O(V)
//     DFS Stack     : O(V)


class Solution {
    public boolean isBipartite(int[][] graph) {
        // 0 → Uncolored (Not Visited), 1 → Color A, 2 → Color B
        int n = graph.length;
        int[] color = new int[n];

        // Handle disconnected graph
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                color[i] = 1;
                if (!dfs(graph, color, i)) {
                    return false;
                }
            }
        }
        
        return true;
    }

    private boolean dfs(int[][] graph, int[] color, int node) {
        // Traverse all neighbors of the current node
        for (int neigh : graph[node]) {
            // Neighbor is not colored yet
            if (color[neigh] == 0) {
                // Assign opposite color
                color[neigh] = color[node] == 1 ? 2 : 1;

                // If conflict is found deeper in DFS
                if (!dfs(graph, color, neigh)) {
                    return false;
                }
            }
            // Neighbor already has the same color -> Not Bipartite
            else if (color[neigh] == color[node]) {
                return false;
            }
        }

        // No conflicts found
        return true;
    }
}
