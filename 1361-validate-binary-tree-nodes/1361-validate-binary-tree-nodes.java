// Problem: Validate Binary Tree Nodes
// Link: https://leetcode.com/problems/validate-binary-tree-nodes/
// Difficulty: Medium

// Approach:
// Use root detection + DFS + visited set.
//
// 1. Find the root by collecting every node that appears as a child.
// 2. The only node that never appears as a child must be the root.
// 3. If no such node exists, return false because a valid tree needs one root.
// 4. Start DFS from the root and mark every visited node.
// 5. If a child is already visited, a cycle or multiple-parent connection exists,
//    so return false.
// 6. After DFS, check whether all n nodes were visited.
//    If not, the graph is disconnected.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int root = findRoot(n, leftChild, rightChild);

        if (root == -1) {
            return false;
        }

        Stack<Integer> st = new Stack<>();
        Set<Integer> seen = new HashSet<>();
        st.push(root);
        seen.add(root);

        while (!st.isEmpty()) {
            int node = st.pop();
            int[] children = new int[] { leftChild[node], rightChild[node] };

            for (int child : children) {
                if (child == -1) {
                    continue;
                }

                if (seen.contains(child)) {
                    return false;
                }

                st.push(child);
                seen.add(child);
            }
        }

        return seen.size() == n;
    }

    private int findRoot(int n, int[] left, int[] right) {
        Set<Integer> children = new HashSet<>();

        for (int node : left) {
            children.add(node);
        }

        for (int node : right) {
            children.add(node);
        }

        for (int i = 0; i < n; i++) {
            if (!children.contains(i)) {
                return i;
            }
        }

        return -1;
    }
}
