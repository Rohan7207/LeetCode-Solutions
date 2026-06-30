// Problem: Increasing Order Search Tree
// Link: https://leetcode.com/problems/increasing-order-search-tree/
// Difficulty: Easy

// Approach:
// In a Binary Search Tree, inorder traversal gives nodes in increasing order.
// We need to rearrange the tree so that:
//     - Every node has no left child
//     - Every node only has a right child
//     - Nodes appear in increasing order
// Use inorder DFS with two pointers:
//     prev:
//         Stores the previously processed node.
//     newRoot:
//         Stores the first node of the new tree.
// Step 1:
// Perform inorder traversal:
//        left subtree
//        current node
//        right subtree
// Step 2:
// When visiting a node:
//     If newRoot is null:
//          This is the smallest element,
//          make it the root of the new tree.
//     Connect previous node to current node:
//          prev.right = root
//     Remove left pointer:
//          root.left = null
//     Move prev forward:
//          prev = root
// Step 3:
// Continue traversal until all nodes are rearranged.
// Return newRoot.


// Time Complexity: O(n)
// Space Complexity: O(h)
//     h = height of tree (recursion stack)


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    TreeNode prev = null;
    TreeNode newRoot = null;

    public TreeNode increasingBST(TreeNode root) {
        dfs(root);

        return newRoot;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.left);

        if (newRoot == null) {
            newRoot = root;
        }

        if (prev != null) {
            prev.right = root;
        }

        root.left = null;

        prev = root;

        dfs(root.right);
    }
}
