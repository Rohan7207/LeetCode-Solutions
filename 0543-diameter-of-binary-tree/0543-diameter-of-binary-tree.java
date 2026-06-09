// Problem: Diameter of Binary Tree
// Link: https://leetcode.com/problems/diameter-of-binary-tree/
// Difficulty: Easy

// Approach:
// Diameter of a binary tree is the length of the
// longest path between any two nodes.
// The longest path passing through a node is:
//     left subtree height + right subtree height
// Use postorder traversal (left, right, root)
// to compute heights while simultaneously
// calculating the diameter.
// For every node:
//     - Recursively find left subtree height
//     - Recursively find right subtree height
//     - Current diameter passing through node:
//           left + right
//     - Update global diameter if current path
//       is larger than previous answer
//     - Return height of current node:
//           max(left, right) + 1
// After traversing all nodes,
// the global variable contains the maximum diameter.

// Time Complexity: O(n)
// Space Complexity: O(h)


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
    int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return res;
    }

    private int height(TreeNode root) {
        if (root == null) return 0;

        int left = height(root.left);
        int right = height(root.right);

        res = Math.max(res, left + right);
        
        return Math.max(left, right) + 1;
    }
}
