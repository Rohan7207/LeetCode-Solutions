// Problem: Count the Number of Complete Components
// Link: https://leetcode.com/problems/count-the-number-of-complete-components/?envType=daily-question&envId=2026-07-11
// Difficulty: Medium

// Approach:
// Every connected component should be checked independently.
// A connected component having k vertices is complete
// if and only if it contains exactly
//      k * (k - 1) / 2
// edges.
// Build an adjacency list for the graph.
// Traverse every node.
//     • If the node is already visited,
//       it belongs to a previously explored component.
//     • Otherwise,
//       start a DFS to explore its entire connected component.
// During DFS:
//     • Count every visited node as one vertex.
//     • Add the degree (adjacency list size) of every visited node
//       to the edge count.
// Since the graph is undirected,
// every edge is counted twice
// (once from each endpoint).
// Therefore,
//     actualEdges = edgeCount / 2
// After DFS finishes:
//     • Let vertices = number of visited nodes.
//     • Let actualEdges = edgeCount / 2.
// If
//     actualEdges == vertices * (vertices - 1) / 2
// then every pair of vertices is connected,
// so this connected component is complete.
// Count all such components.

// Time Complexity:
//     Building Adjacency List : O(E)
//     DFS Traversal           : O(V + E)
//     Total                   : O(V + E)
//
// Space Complexity:
//     Adjacency List : O(V + E)
//     Visited Array  : O(V)
//     DFS Stack      : O(V)


class Solution {
    private int vertice;
    private int edge;

    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] currEdge : edges) {
            int u = currEdge[0];
            int v = currEdge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] vis = new boolean[n];
        int completeComponent = 0;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                vertice = 0;
                edge = 0;
                dfs(adj, i, vis);

                edge /= 2;

                if (edge == (vertice * (vertice - 1) / 2)) {
                    completeComponent++;
                }
            }
        }

        return completeComponent;
    }

    private void dfs(List<List<Integer>> adj, int node, boolean[] vis) {
        vis[node] = true;
        vertice++;
        edge += adj.get(node).size();

        for (int neigh : adj.get(node)) {
            if (!vis[neigh]) {
                dfs(adj, neigh, vis);
            }
        }
    }
}
