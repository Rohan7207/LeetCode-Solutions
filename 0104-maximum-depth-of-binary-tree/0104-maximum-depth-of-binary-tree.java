// Problem: Maximum Depth of Binary Tree 
// Link: https://leetcode.com/problems/maximum-depth-of-binary-tree/
// Difficulty: Easy

// Approach:
// Use recursion to calculate the depth
// of left and right subtrees.
// If the current node is null,
// return 0.
// Recursively find:
//     - depth of left subtree
//     - depth of right subtree
// Return the maximum of both depths
// plus 1 for the current root node.

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
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int h1 = maxDepth(root.left);
        int h2 = maxDepth(root.right);

        return Math.max(h1, h2) + 1;
    }
}
