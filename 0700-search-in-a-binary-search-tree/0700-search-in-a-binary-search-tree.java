// Problem: Search in a Binary Search Tree
// Link: https://leetcode.com/problems/search-in-a-binary-search-tree/
// Difficulty: Easy

// Approach:
// We need to find a node with value = val
// in a Binary Search Tree (BST).
// Use the BST property:
//     left subtree  -> values smaller than root
//     right subtree -> values greater than root
// Start from root.
// Case 1:
//     root == null
//     Value not found.
//     Return null.
// Case 2:
//     root.val == val
//     Target found.
//     Return current node.
// Case 3:
//     val < root.val
//     Target can only exist in left subtree.
//     Search left.
// Case 4:
//     val > root.val
//     Target can only exist in right subtree.
//     Search right.
// Continue recursively until
// node is found or tree ends.

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
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;

        if (root.val > val) {
            return searchBST(root.left, val);
        } else if (root.val == val) {
            return root;
        } else {
            return searchBST(root.right, val);
        }
    }
}
