class Solution {

    int[] parent;
    int[] rank;

    public int makeConnected(int n, int[][] connections) {
        if(connections.length < n - 1) {
            return -1;
        }

        parent = new int[n];
        rank = new int[n];

        // Initially, every node is its own parent
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int components = n;
        for(int[] connection : connections) {
            int u = connection[0];
            int v = connection[1];

            // If they belong to different components,
            // connect those components.
            if(union(u, v)) {
                components--;
            }
        }

        return components - 1;
    }

    private int find(int x) {
        if(parent[x] == x) {
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
        }
        else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        }
        else {
            parent[rootB] = rootA;
            rank[rootA]++;
        }

        return true;
    }
}

/*
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
*/