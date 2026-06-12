// Problem: N-ary Tree Postorder Traversal
// Link: https://leetcode.com/problems/n-ary-tree-postorder-traversal/
// Difficulty: Easy

// Approach:
// Perform postorder traversal of an N-ary tree.
// Postorder traversal follows:
//     Children (left to right)
//     Root
// Start DFS from the root node.
// For every node:
//     - Recursively traverse all children
//       from left to right
//     - After all children are processed,
//       add the current node value to answer
// If the node is null:
//     - Return immediately
// The traversal order becomes:
//     Child1 -> Child2 -> ... -> ChildN -> Root

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
}
*/

class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();

        dfs(root, ans);
        return ans;
    }

    private void dfs(Node root, List<Integer> ans) {
        if (root == null)
            return;

        for (Node child : root.children) {
            dfs(child, ans);
        }

        ans.add(root.val);
    }
}
