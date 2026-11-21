// Problem: Find Subtree Sizes After Changes
// Link: https://leetcode.com/problems/find-subtree-sizes-after-changes/?envType=daily-question&envId=2026-11-20
// Difficulty: Medium

// Approach:
// 1. Build an adjacency list representing the original tree using parent[].
// 2. Initialize res[i] = 1 because every node contributes to its own subtree.
// 3. Maintain last[26], where last[c] stores the nearest ancestor
//    encountered on the current DFS path having character c.
// 4. During DFS:
//    a. Save the previous last[c] in nearestAncestor.
//    b. Update last[c] = currNode.
//    c. Recursively process all children.
//    d. Restore last[c] = nearestAncestor (backtracking).
// 5. After processing children, determine the effective parent:
//    - If a matching ancestor exists, use nearestAncestor.
//    - Otherwise, retain the original parent.
// 6. Add res[currNode] to res[newParent].
// 7. Return res.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {

    private List<List<Integer>> adjList;
    private int[] res;
    private int[] last;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // parent[i] = node, node is parent of ith node
        for (int i = 1; i < n; i++) {
            int u = parent[i];
            int v = i;

            adjList.get(u).add(v);
        }

        res = new int[n];
        last = new int[26];

        Arrays.fill(res, 1);
        Arrays.fill(last, -1);

        dfs(0, s, parent);

        return res;
    }

    private void dfs(int currNode, String s, int[] parent) {
        int c = s.charAt(currNode) - 'a';
        int nearestAncestor = last[c];

        last[c] = currNode; // For child of currNode, currNode is nearestAncestor for all child
        for (int child : adjList.get(currNode)) {
            dfs(child, s, parent);
        }

        // Back to currNode update currNode ancestor as its correct ancestor which nearestAncestor
        last[c] = nearestAncestor;

        // If currNode is root, it has no ancestor
        if (currNode != 0) {
            int newParent = parent[currNode]; // oldParent

            if (nearestAncestor != -1) {
                newParent = nearestAncestor;
            }

            parent[currNode] = newParent;
            
            res[newParent] += res[currNode];
        }
    }
}
