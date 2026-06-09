// Problem: Number of Provinces
// Link: https://leetcode.com/problems/number-of-provinces/
// Difficulty: Medium

// Approach:
// Treat each city as a node in a graph.
// The given matrix represents connections:
//     isConnected[i][j] = 1
//     => city i and city j are directly connected
// A province is a group of directly or indirectly
// connected cities.
// Use DFS to find all cities belonging to the
// same connected component.
// Maintain a visited array.
// Traverse every city:
//     - If the city is not visited:
//           Start DFS from that city
//           DFS will visit all cities
//           in the same province
//           Increment province count
// During DFS:
//     - Mark current city as visited
//     - Traverse all neighboring cities
//     - If a city is connected and not visited:
//           recursively visit it
// After all cities are processed,
// province count represents the number
// of connected components.

// Time Complexity: O(n²)
// Space Complexity: O(n)


class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] vis = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(isConnected, vis, i);
                provinces++;
            }
        }

        return provinces;
    }

    private void dfs(int[][] graph, boolean[] vis, int node) {
        vis[node] = true;

        for (int i = 0; i < graph.length; i++) {
            if (graph[node][i] == 1 && !vis[i]) {
                dfs(graph, vis, i);
            }
        }
    }
}
