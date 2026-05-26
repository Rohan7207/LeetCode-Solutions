// Problem: Lowest Common Ancestor of a Binary Search Tree
// Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
// Difficulty: Medium

// Approach:
// Use BST property:
//     left subtree values < root
//     right subtree values > root
// For every node:
//     - If both p and q are greater than root,
//       LCA must be in right subtree.
//
//     - If both p and q are smaller than root,
//       LCA must be in left subtree.
//
//     - Otherwise current root is the split point,
//       meaning one node lies on each side
//       (or one node equals root).
// Return the current root as LCA.

// Time Complexity: O(h)
// Space Complexity: O(h) recursion
// h = height of BST


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        return root;
    }
}
