// Problem: Count Complete Tree Nodes
// Link: https://leetcode.com/problems/count-complete-tree-nodes/
// Difficulty: Easy

// Approach:
// Use recursion to count nodes in the binary tree.
// For each node:
//     - Recursively count nodes in left subtree.
//     - Recursively count nodes in right subtree.
//     - Add 1 for the current node.
// If the current node is null,
// return 0.
// Return total nodes as:
//     left count + right count + 1.

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
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        
        int leftcount = countNodes(root.left);
        int rightcount = countNodes(root.right);

        return leftcount + rightcount + 1;
    }
}
