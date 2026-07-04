class Solution {
    int ans = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {
        List<List<int[]>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];

            adj.get(u).add(new int[] {v, w});
            adj.get(v).add(new int[] {u, w}); // Undirected graph so u -> v and v -> u
        }

        boolean[] vis = new boolean[n + 1];

        dfs(adj, 1, vis);

        return ans;
    }

    private void dfs(List<List<int[]>> adj, int node, boolean[] vis) {
        vis[node] = true;

        for (int[] edge : adj.get(node)) {
            int neigh = edge[0];
            int wt = edge[1];

            ans = Math.min(ans, wt);

            if (!vis[neigh]) {
                dfs(adj, neigh, vis);
            }
        }
    }
}

/*
    Queue<Integer> q = new LinkedList<>();
    boolean[] vis = new boolean[n + 1];

    q.offer(1);
    vis[1] = true;

    int ans = Integer.MAX_VALUE;

    while (!q.isEmpty()) {
        int node = q.poll();

        for (Edge edge : adj.get(node)) {

            // Update minimum edge weight in this connected component
            ans = Math.min(ans, edge.weight);

            if (!vis[edge.to]) {
                vis[edge.to] = true;
                q.offer(edge.to);
            }
        }
    }
*/