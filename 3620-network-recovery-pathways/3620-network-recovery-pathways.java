// Problem: Network Recovery Pathways
// Link: https://leetcode.com/problems/network-recovery-pathways/?envType=daily-question&envId=2026-07-03
// Difficulty: Hard

// Approach:
// Observation:
// The score of a path is defined as the minimum edge cost
// present on that path (called the bottleneck).
// We need to maximize this bottleneck while keeping the
// total path cost <= k.
//
// Step 1: Build Graph Once
// Convert the edge list into an adjacency list.
// Since the graph never changes during binary search,
// build it only once.
// Also compute one Topological Ordering of the DAG
// using Kahn's Algorithm.
//
// Step 2: Find Binary Search Range
// Traverse every edge.
// low  = minimum edge weight
// high = maximum edge weight
// The answer must lie within this range.
//
// Step 3: Binary Search on Answer
// Suppose mid is our candidate bottleneck.
// Question:
// "Can there exist a path from 0 to n-1 whose minimum
// edge cost is at least mid while total cost <= k?"
// If YES:
//      Try larger bottleneck.
// If NO:
//      Try smaller bottleneck.
//
// Step 4: check(mid)
// Initialize:
// dp[node] = minimum total cost required
//            to reach this node.
// dp[0] = 0
// others = INF
// Traverse nodes in Topological Order.
// For every outgoing edge:
//      u ----weight----> v
// Condition 1:
// Ignore edge if
//      weight < mid
// because this edge would reduce the bottleneck below mid.
// Condition 2:
// Ignore offline intermediate nodes.
// Condition 3:
// Relax:
// dp[v] = min(dp[v], dp[u] + weight)
//
// Step 5:
// After processing all nodes:
// if dp[n-1] <= k
//      candidate bottleneck is possible
// else
//      impossible.
// Binary Search finally returns the largest feasible bottleneck.

// Time Complexity:
//
// Graph Construction      : O(E)
// Topological Sort        : O(V + E)
//
// Binary Search iterations: O(log W)
// where
// W = maximum edge weight.
//
// Each check():
//      DP on DAG = O(V + E)
//
// Overall:
//
// O((V + E) * log W)
//
// Space Complexity:
//
// Graph        : O(V + E)
// Topological  : O(V)
// DP           : O(V)
//
// Overall:
//
// O(V + E)


class Solution {
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    List<Integer> topoOrder = new ArrayList<>();
    List<List<Edge>> adjList = new ArrayList<>();

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        // Maximum Bottleneck Path
        // - Budget Constraint
        // - DAG Dynamic Programming / Topological Order

        // Step 1: Build Adjacency List
        // Using an edge structure to keep track of both destination and weight
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
        int n = online.length;

        // Step 3: Initialize DP array
        long INF = Long.MAX_VALUE;
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
