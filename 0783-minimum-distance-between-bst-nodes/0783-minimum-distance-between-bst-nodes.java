// Problem: Minimum Distance Between BST Nodes
// Link: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
// Difficulty: Easy

// Approach:
// We need to find the minimum absolute difference
// between values of any two nodes in a BST.
// Key BST property:
//     Inorder traversal of BST gives values in sorted order.
// Example:
//     BST values after inorder:
//     [1, 3, 4, 6, 8]
// Minimum difference in a sorted array always occurs
// between adjacent elements.
// So instead of comparing every pair,
// we only compare current node with previous visited node
// during inorder traversal.
// Maintain:
//     prev -> previous node value in inorder traversal
//     min  -> minimum difference found so far
// Inorder traversal:
//     1. Visit left subtree
//     2. Process current node
//     3. Visit right subtree
// While processing current node:
//     If prev is not null:
//         diff = root.val - prev
//         min = Math.min(min, diff)
//     Then update:
//         prev = root.val
// Finally return min.

// Time Complexity: O(n)
// Space Complexity: O(h)
//
// n = number of nodes
// h = height of tree due to recursion stack


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
    int min = Integer.MAX_VALUE;
    Integer prev = null;

    public int minDiffInBST(TreeNode root) {
        if (root == null)
            return min;

        minDiffInBST(root.left);

        if (prev != null) {
            min = Math.min(min, root.val - prev);
        }

        prev = root.val;

        minDiffInBST(root.right);

        return min;
    }
}
