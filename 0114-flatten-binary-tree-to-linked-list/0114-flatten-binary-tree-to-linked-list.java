// Problem: Flatten Binary Search Tree to Linked List
// Link: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
// Difficulty: Medium

// Approach:
// Use recursion (postorder style) to flatten the tree.
// For each node:
//     - Recursively flatten left subtree.
//     - Recursively flatten right subtree.
//     - If left subtree exists:
//         - Attach the original right subtree
//           to the rightmost node of left subtree.
//         - Move left subtree to right side.
//         - Set left pointer to null.
// Return the rightmost node of the flattened subtree.

// Time Complexity: O(n)
// Space Complexity: O(n)


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
    public void flatten(TreeNode root) {
        flattenTree(root);
    }

    private TreeNode flattenTree(TreeNode root) {
        if (root == null) return null;

        if (root.left == null && root.right == null) return root;

        TreeNode left = flattenTree(root.left);
        TreeNode right = flattenTree(root.right);

        if (left != null) {
            left.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        return (right == null) ? left : right;
    }
}
