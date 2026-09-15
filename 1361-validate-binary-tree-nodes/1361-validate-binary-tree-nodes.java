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

        // DFS
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

/*
    Process in validating a binary tree:

- If a binary tree does not have a root, then findRoot will return -1.
- If there is a node with more than one parent, then we will detect it with seen.
- If the tree is disconnected, then seen will hold less than n nodes at the end.
- If there is a cycle, then we will detect it with seen.

    // For BFS use queue instead of stack
*/