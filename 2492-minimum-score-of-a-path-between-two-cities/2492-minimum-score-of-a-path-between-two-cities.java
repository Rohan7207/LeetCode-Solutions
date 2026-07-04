// Problem : Minimum Score of a Path Between Two Cities
// Link : https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/?envType=daily-question&envId=2026-07-04
// Difficulty : Medium

// Approach:
// The score of a path is the minimum edge weight
// present on that path.
// Unlike normal path problems, we are allowed to
// revisit cities and roads multiple times.
// This means if two cities belong to the same
// connected component, we can freely move around
// inside that component before finally reaching
// city n.
// Therefore, the answer is NOT the minimum edge
// on one particular path.
// Instead, it is simply the minimum edge weight
// present in the connected component containing
// city 1.
//
// Step 1:
// Build the adjacency list for the undirected graph.
// Store:
//      {neighbor, edgeWeight}
// for every road.
//
// Step 2:
// Start DFS from city 1.
// Visit every reachable city exactly once.
//
// Step 3:
// For every traversed edge:
//      ans = min(ans, edgeWeight)
// This guarantees we record the smallest edge
// inside the connected component.
//
// Step 4:
// Continue DFS until every reachable node
// has been visited.
// Return the minimum edge weight found.

// Time Complexity:
// Building Graph : O(E)
// DFS Traversal : O(V + E)
// Overall:
// O(V + E)
//
// Space Complexity:
// Graph : O(V + E)
// Visited Array : O(V)
// DFS Stack : O(V)
// Overall:
// O(V + E)


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
