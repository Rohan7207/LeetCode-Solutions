// Problem: Number of Operations to Make Network Connected
// Link: https://leetcode.com/problems/number-of-operations-to-make-network-connected/
// Difficulty: Medium

// Approach:
// Use Disjoint Set Union (DSU) / Union-Find.
//
// 1. If the number of connections is less than n - 1,
//    connecting all computers is impossible.
//
// 2. Initially, every computer is a separate component,
//    so components = n.
//
// 3. Initialize parent[i] = i, meaning every node is its own parent.
//
// 4. For every connection [u, v], find the roots of u and v.
//
// 5. If the roots are different, merge their components using
//    union by rank and decrease the component count by 1.
//
// 6. If the roots are the same, the computers are already connected,
//    so that connection is an extra cable.
//
// 7. Finally, to connect `components` separate groups, we need
//    components - 1 cables.
//
// 8. Path compression makes future find operations faster.

// Time Complexity: O(n + m α(n))
// Space Complexity: O(n)


class Solution {

    int[] parent;
    int[] rank;

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        parent = new int[n];
        rank = new int[n];

        // Initially, every node is its own parent
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int components = n;
        for (int[] connection : connections) {
            int u = connection[0];
            int v = connection[1];

            // If they belong to different components,
            // connect those components.
            if (union(u, v)) {
                components--;
            }
        }

        return components - 1;
    }

    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        // Path compression
        return parent[x] = find(parent[x]);
    }

    private boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        // Already connected
        if (rootA == rootB) {
            return false;
        }

        // Union by rank
        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootB] = rootA;
            rank[rootA]++;
        }

        return true;
    }
}
