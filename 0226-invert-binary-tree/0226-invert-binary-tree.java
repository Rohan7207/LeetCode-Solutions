// Problem: Invert Binary Tree
// Link: https://leetcode.com/problems/invert-binary-tree/
// Difficulty: Easy

// Approach:
// Use recursion to traverse the tree.
// For every node:
//     - Recursively invert the left subtree.
//     - Recursively invert the right subtree.
//     - Swap the left and right child pointers.
// Continue until all nodes are processed.
// Return the root of the inverted tree.

// Time Complexity: O(n)
// Space Complexity: O(h) // recursion stack


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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;
        return root;
    }
}
