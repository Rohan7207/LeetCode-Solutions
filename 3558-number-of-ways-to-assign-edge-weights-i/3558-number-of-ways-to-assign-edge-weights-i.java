class Solution {
    int maxDepth = 0;
    int MOD = 1000000007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dfs(graph, 1, -1, 0);
        return power(2, maxDepth - 1);
    }

    private void dfs(List<List<Integer>> graph, int node, int parent, int depth) {
        maxDepth = Math.max(maxDepth, depth);

        for (int child : graph.get(node)) {
            if (parent == child) continue;

            dfs(graph, child, node, depth + 1);
        }
    }

    private int power(long base, int exp) {
        long res = 1;

        while (exp > 0) {
            if (exp % 2 == 1) {
                res = (res * base) % MOD;
            }

            base = (base * base) % MOD;
            exp /= 2;
        }

        return (int) res;
    }
}