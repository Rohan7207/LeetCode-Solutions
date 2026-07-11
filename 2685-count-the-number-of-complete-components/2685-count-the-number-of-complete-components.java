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