// Problem: Palindromic Path Queries in a Tree
// Link: https://leetcode.com/problems/palindromic-path-queries-in-a-tree/?envType=daily-question&envId=2026-10-29
// Difficulty: Hard

// Approach:
// 1. Build an adjacency list to represent the undirected tree.
//
// 2. Perform an Euler Tour DFS from root 0.
//    - Record tin[node] when entering a node.
//    - Record tout[node] when leaving a node.
//    - Store the node's character as a 26-bit mask at both positions.
//    - Record each node's depth and immediate parent.
//
// 3. Build the Binary Lifting ancestor table.
//    - Precompute 2^j-th ancestors for every node.
//    - Use this table to find LCA(u, v) in O(log n).
//
// 4. Build a Segment Tree over the Euler Tour array.
//    - Each leaf stores a character bitmask.
//    - Each internal node stores the XOR of its children.
//    - Prefix XOR queries give the root-to-node path mask.
//
// 5. Process update queries.
//    - Change the node's character.
//    - Update both tin[node] and tout[node] in the Segment Tree.
//
// 6. Process path queries (u, v).
//    - Find lca = LCA(u, v).
//    - Calculate:
//        mask = prefixXOR(u) ^ prefixXOR(v) ^ charMask(lca)
//    - The LCA's character is added back because it cancels
//      when the two root-path masks are XORed.
//
// 7. Check whether a palindrome permutation is possible.
//    - Each set bit represents a character with odd frequency.
//    - If at most one bit is set, the path can form a palindrome.
//    - Check using (mask & (mask - 1)) == 0.

// Time Complexity: O(n log n + q log n)
// Space Complexity: O(n log n)


class Solution {
    private int n;

    // Graph: adjacency list for the undirected tree
    private List<List<Integer>> adj;

    // Euler Tour: entry time, exit time, and XOR mask at each position
    private int[] tin, tout, eulerTour;
    private int time;

    // Segment Tree: supports range XOR queries and point updates
    private int[] segmentTree;

    // Binary Lifting: ancestorTable[node][j] = 2^j-th ancestor
    private int[][] ancestorTable;
    private int[] depth;
    private int cols;

    // Mutable character labels: Java String itself cannot be modified
    private char[] labels;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        this.n = n;
        this.labels = s.toCharArray();

        // 1. Initialize the adjacency list
        adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // 2. Populate the undirected tree
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // 3. Initialize Euler Tour arrays
        tin = new int[n];
        tout = new int[n];
        depth = new int[n];

        // Every node occurs twice: entry and exit
        eulerTour = new int[2 * n];

        // 4. Initialize Binary Lifting table
        cols = 32 - Integer.numberOfLeadingZeros(n);
        ancestorTable = new int[n][cols];

        for (int[] row : ancestorTable) {
            Arrays.fill(row, -1);
        }

        // 5. DFS from root 0 to construct the Euler Tour
        // and initialize each node's parent and depth
        time = 0;
        dfs(0, -1, 0);

        // Build the ancestor table after DFS initializes parents
        buildAncestorTable();

        // 6. Build the Segment Tree over the Euler Tour
        int N = 2 * n;
        segmentTree = new int[4 * N];

        buildSegmentTree(0, 0, N - 1, eulerTour);

        List<Boolean> result = new ArrayList<>();

        // 7. Process queries
        for (String q : queries) {
            String[] parts = q.trim().split("\\s+");

            // Update query: "update node character"
            if (parts[0].equalsIgnoreCase("update")) {
                int node = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);

                int newMask = charMask(newChar);

                // Update the character label
                labels[node] = newChar;

                // Both Euler positions represent this node.
                // Replace their old masks with the new mask.
                updateSegmentTree(
                        tin[node], newMask, 0, 0, 2 * n - 1);

                updateSegmentTree(
                        tout[node], newMask, 0, 0, 2 * n - 1);

            } else {
                // Path query: "query u v"
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                // Find Lowest Common Ancestor
                int lcaNode = getLCA(u, v);

                // XOR masks of the root-to-u and root-to-v paths
                int prefixU = querySegmentTree(
                        0, tin[u], 0, 0, 2 * n - 1);

                int prefixV = querySegmentTree(
                        0, tin[v], 0, 0, 2 * n - 1);

                // The LCA cancels out of the two prefixes,
                // so XOR its mask once to include it in the path.
                int mask = prefixU ^ prefixV
                        ^ charMask(labels[lcaNode]);

                // A palindrome permutation is possible if
                // at most one character has an odd frequency.
                result.add((mask & (mask - 1)) == 0);
            }
        }

        return result;
    }

    // Convert a lowercase character into a 26-bit mask
    private int charMask(char c) {
        return 1 << (c - 'a');
    }

    // DFS: record entry/exit times, character masks, parents and depths
    private void dfs(int node, int parent, int d) {
        depth[node] = d;
        ancestorTable[node][0] = parent;

        // Entry event
        tin[node] = time;
        eulerTour[time] = charMask(labels[node]);
        time++;

        for (int child : adj.get(node)) {
            if (child == parent) {
                continue;
            }

            dfs(child, node, d + 1);
        }

        // Exit event
        tout[node] = time;
        eulerTour[time] = charMask(labels[node]);
        time++;
    }

    // Segment Tree: build for XOR
    private void buildSegmentTree(
            int i, int l, int r, int[] eulerTour) {

        // Leaf represents one Euler Tour position
        if (l == r) {
            segmentTree[i] = eulerTour[l];
            return;
        }

        int mid = l + (r - l) / 2;

        buildSegmentTree(2 * i + 1, l, mid, eulerTour);
        buildSegmentTree(2 * i + 2, mid + 1, r, eulerTour);

        // Combine the two children using XOR
        segmentTree[i] = segmentTree[2 * i + 1]
                ^ segmentTree[2 * i + 2];
    }

    // Segment Tree: point assignment update
    private void updateSegmentTree(
            int index, int val, int i, int l, int r) {

        if (l == r) {
            segmentTree[i] = val;
            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            updateSegmentTree(index, val, 2 * i + 1, l, mid);
        } else {
            updateSegmentTree(index, val, 2 * i + 2, mid + 1, r);
        }

        // Recalculate the current node after updating its child
        segmentTree[i] = segmentTree[2 * i + 1]
                ^ segmentTree[2 * i + 2];
    }

    // Segment Tree: range XOR query over [start, end]
    private int querySegmentTree(
            int start, int end, int i, int l, int r) {

        // No overlap
        if (l > end || r < start) {
            return 0;
        }

        // Complete overlap
        if (start <= l && r <= end) {
            return segmentTree[i];
        }

        // Partial overlap: combine both children
        int mid = l + (r - l) / 2;

        int leftXor = querySegmentTree(
                start, end, 2 * i + 1, l, mid);

        int rightXor = querySegmentTree(
                start, end, 2 * i + 2, mid + 1, r);

        return leftXor ^ rightXor;
    }

    // Binary Lifting: build the ancestor table
    private void buildAncestorTable() {
        for (int j = 1; j < cols; j++) {
            for (int node = 0; node < n; node++) {
                int middle = ancestorTable[node][j - 1];

                if (middle != -1) {
                    ancestorTable[node][j] = ancestorTable[middle][j - 1];
                }
            }
        }
    }

    // Find LCA(u, v) using Binary Lifting
    private int getLCA(int u, int v) {

        // Make u the deeper node
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Bring u to the same depth as v
        int diff = depth[u] - depth[v];

        for (int j = 0; j < cols; j++) {
            if ((diff & (1 << j)) != 0) {
                u = ancestorTable[u][j];
            }
        }

        if (u == v) {
            return u;
        }

        // Lift both nodes until their parents are the same
        for (int j = cols - 1; j >= 0; j--) {
            if (ancestorTable[u][j] != ancestorTable[v][j]) {
                u = ancestorTable[u][j];
                v = ancestorTable[v][j];
            }
        }

        return ancestorTable[u][0];
    }
}
