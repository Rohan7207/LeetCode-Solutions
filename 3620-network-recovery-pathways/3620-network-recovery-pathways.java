class Solution {
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        // Maximum Bottleneck Path
        // - Budget Constraint
        // - DAG Dynamic Programming / Topological Order

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        for (int[] edge : edges) {
            low = Math.min(low, edge[2]);
            high = Math.max(high, edge[2]);
        }

        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isValidPathWay(edges, online, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean isValidPathWay(int[][] edges, boolean[] online, long k, int mid) {
        // Step 1: Build Adjacency List
        // Using an edge structure to keep track of both destination and weight
        List<List<Edge>> adjList = new ArrayList<>();
        int n = online.length;

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adjList.get(u).add(new Edge(v, w));
        }

        // Step 2: Topological Sort (Kahn's Algorithm or DFS)
        int[] indegree = new int[n];
        for (int u = 0; u < n; u++) {
            for (Edge edge : adjList.get(u)) {
                indegree[edge.to]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            topoOrder.add(u);
            for (Edge edge : adjList.get(u)) {
                int v = edge.to;
                indegree[v]--;
                if (indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        // Step 3: Initialize DP array
        long INF = Long.MAX_VALUE; // Safe value to prevent integer overflow during relaxation
        long[] dp = new long[n];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        // Step 4: Process each node in topological order
        for (int u : topoOrder) {
            // Unreachable nodes cannot relax their neighbors
            if (dp[u] == INF)
                continue;

            for (Edge edge : adjList.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                // Condition A: If edge cost < mid -> skip
                if (weight < mid) {
                    continue;
                }

                // Condition B: If destination offline (except last node) -> skip
                if (v != n - 1 && !online[v]) {
                    continue;
                }

                // Condition C: Relax DP
                dp[v] = Math.min(dp[v], weight + dp[u]);
            }
        }

        return dp[n - 1] <= k;
    }
}