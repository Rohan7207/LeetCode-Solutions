class Solution {
    public int makeConnected(int n, int[][] connections) {
        int len = connections.length;

        if (len < n - 1) {
            return -1;
        }

        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] connection : connections) {
            int u = connection[0];
            int v = connection[1];

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        boolean[] vis = new boolean[n];
        int components = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(adjList, i, vis);
                components++;
            }
        }

        return components - 1;
    }

    private void dfs(List<List<Integer>> adjList, int node, boolean[] vis) {
        if (vis[node]) {
            return;
        }

        vis[node] = true;

        for (int val : adjList.get(node)) {
            dfs(adjList, val, vis);
        }
    }
}

// 1 - 4, 3,
// 0 - 3, 1, 0
// 3 - 7, 6
// 2 - 7, 4
// 5 - 6, 7
// 6 - 7,
// 4 - 7,
