// Problem: N-ary Tree Preorder Traversal
// Link: https://leetcode.com/problems/n-ary-tree-preorder-traversal/
// Difficulty: Easy

// Approach:
// Perform preorder traversal of an N-ary tree.
// Preorder traversal follows:
//     Root
//     Children (left to right)
// Start DFS from the root node.
// For every node:
//     - Visit current node first
//       and add its value to answer
//     - Recursively traverse all children
//       from left to right
// Continue until all nodes are visited.
// If the node is null:
//     - Return immediately
// The traversal order becomes:
//     Root -> Child1 -> Child2 -> ... -> ChildN

// Time Complexity: O(n)
// Space Complexity: O(h)


/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    List<Integer> ans = new ArrayList<>();

    public List<Integer> preorder(Node root) {
        dfs(root);
        return ans;
    }

    private void dfs(Node root) {
        if (root == null)
            return;

        ans.add(root.val);

        for (Node child : root.children) {
            dfs(child);
        }
    }
}
