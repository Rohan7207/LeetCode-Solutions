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