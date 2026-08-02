// Problem: Univalued Binary Tree
// Link: https://leetcode.com/problems/univalued-binary-tree/
// Difficulty: Easy

// Approach:
// The tree is considered univalued if every node has the same
// value as the root.
//
// First, handle the empty tree. An empty tree is trivially
// univalued.
//
// Store the root's value and recursively traverse the entire tree.
//
// For every node:
// - If the node is null, return true since it does not violate
//   the condition.
// - If the node's value differs from the root's value,
//   immediately return false.
// - Otherwise, recursively check both the left and right subtrees.
//
// The tree is univalued only if both subtrees also satisfy
// the condition.

// Time Complexity:
// O(n)
//
// Space Complexity:
// O(h)
// where h is the height of the tree (recursion stack).


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
    public boolean isUnivalTree(TreeNode root) {
        if(root == null) {
            return true;
        }

        return helper(root, root.val);
    }

    private boolean helper(TreeNode root, int val) {
        if(root == null) {
            return true;
        }

        if(root.val != val) {
            return false;
        }

        return helper(root.left, val) && helper(root.right, val);
    }
}
