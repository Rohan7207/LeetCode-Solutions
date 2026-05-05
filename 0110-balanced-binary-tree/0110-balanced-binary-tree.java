// Problem: Balanced Binary Tree
// Link: https://leetcode.com/problems/balanced-binary-tree/
// Difficulty: Easy

// Approach:
// Use DFS (postorder traversal) to compute height
// while checking balance at the same time.
// For each node:
//     - Recursively get height of left subtree.
//     - If left is unbalanced (-1), return -1.
//     - Recursively get height of right subtree.
//     - If right is unbalanced (-1), return -1.
//     - If difference > 1 → return -1.
//     - Else return height = max(left, right) + 1.
// In main function:
//     - If result != -1 → tree is balanced.

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
    public boolean isBalanced(TreeNode root) {
        return check(root) != -1;
    }

    private int check(TreeNode node) {
        if (node == null) return 0;

        int left = check(node.left);
        if (left == -1) return -1; // left subtree unbalanced

        int right = check(node.right);
        if (right == -1) return -1; // right subtree unbalanced

        if (Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }
}
