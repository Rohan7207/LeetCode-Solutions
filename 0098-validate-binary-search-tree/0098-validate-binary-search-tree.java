// Problem: Validate Binary Seacrh Tree
// Link: https://leetcode.com/problems/validate-binary-search-tree/
// Difficulty: Medium

// Approach:
// Use recursion with minimum and maximum bounds
// to validate the BST property.
// For each node:
//     - Its value must be greater than min bound.
//     - Its value must be smaller than max bound.
// Recursively validate:
//     - Left subtree with updated max bound as current node.
//     - Right subtree with updated min bound as current node.
// If any node violates the bounds,
// return false.
// Return true if all nodes satisfy BST conditions.

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
    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }

    private boolean isValid(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null)
            return true;
        if (min != null && root.val <= min.val)
            return false;
        if (max != null && root.val >= max.val)
            return false;

        return isValid(root.left, min, root) && isValid(root.right, root, max);
    }
}
