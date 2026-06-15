// Problem: Insert into a Binary Search Tree
// Link: https://leetcode.com/problems/insert-into-a-binary-search-tree/
// Difficulty: Medium

// Approach:
// We need to insert a new value into a BST
// while maintaining BST properties.
// BST Property:
//     left subtree  -> smaller values
//     right subtree -> greater values
// Start from the root.
// Case 1:
//     root == null
//     We found the correct position.
//     Create a new node and return it.
// Case 2:
//     val < root.val
//     Insert into left subtree.
//     root.left = insertIntoBST(root.left, val);
// Case 3:
//     val > root.val
//     Insert into right subtree.
//     root.right = insertIntoBST(root.right, val);
// After insertion,
// return the current root.
// Returning root reconnects the modified
// subtree back to its parent.

// Time Complexity: O(h)
// Space Complexity: O(h)
//
// h = height of BST


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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }

        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        
        return root;
    }
}
