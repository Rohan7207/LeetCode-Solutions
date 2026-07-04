class Solution {
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    List<List<Edge>> adj = new ArrayList<>();
    int ans = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];

            adj.get(u).add(new Edge(v, w));
            adj.get(v).add(new Edge(u, w)); // Undirected graph so u -> v and v -> u
        }

        boolean[] vis = new boolean[n + 1];

        dfs(1, vis);

        return ans;
    }

    private void dfs(int node, boolean[] vis) {
        vis[node] = true;

        for (Edge edge : adj.get(node)) {
            ans = Math.min(ans, edge.weight);

            if (!vis[edge.to]) {
                dfs(edge.to, vis);
            }
        }
    }
}