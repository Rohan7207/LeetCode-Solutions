// Problem: Redundant Connection
// Link: https://leetcode.com/problems/redundant-connection/
// Difficulty: Medium

// Approach:
// Detect the redundant edge in an undirected graph.
// The graph is almost a tree, but one extra edge
// creates a cycle.
// Use DSU / Union Find to detect the cycle.
// Maintain a parent array:
//     parent[i] = representative/root of node i
// Initially:
//     Every node is its own parent.
// For every edge [u, v]:
//     - Find root parent of u
//     - Find root parent of v
//     If both roots are same:
//         u and v are already connected.
//         Adding this edge creates a cycle.
//         So this edge is redundant.
//     Else:
//         Merge both components by connecting
//         one root to another.
// Use path compression in find() to make
// future searches faster.

// Time Complexity: O(n α(n)) ≈ O(n)
// Space Complexity: O(n)


class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[edges.length + 1];
        for (int i = 1; i <= edges.length; i++) {
            parent[i] = i; 
        }

        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            int r1 = find(parent, node1);
            int r2 = find(parent, node2);

            if (r1 == r2) {
                return edge;
            }

            parent[r2] = r1;
        }

        return new int[0];
    }

    private int find(int[] parent, int node) {
        while (node != parent[node]) {
            parent[node] = parent[parent[node]]; 
            node = parent[node]; 
        }
        
        return node;
    }
}
