// Problem: Symmetric Tree
// Link: https://leetcode.com/problems/symmetric-tree/
// Difficulty: Easy

// Approach:
// Use recursion to check whether the tree
// is a mirror of itself.
// Compare two nodes at a time:
//     - If both nodes are null,
//       they are symmetric.
//     - If one node is null,
//       symmetry is broken.
//     - Check whether both node values are equal.
// Recursively compare:
//     - left subtree of first node
//       with right subtree of second node.
//     - right subtree of first node
//       with left subtree of second node.
// Return true only if all mirrored nodes match.

// Time Complexity: O(n)
// Space Complexity: O(n) // recursion stack


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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;

        if (left == null || right == null) return false;

        return (left.val == right.val) &&
                (isMirror(left.left, right.right)) &&
                (isMirror(left.right, right.left));
    }
}
