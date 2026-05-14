// Problem: Binary Tree Preorder Traversal
// Link: https://leetcode.com/problems/binary-tree-preorder-traversal/
// Difficulty: Easy

// Approach:
// Use recursion to perform preorder traversal
// of the binary tree.
// Create a helper function:
//     - Add the current root value to result.
//     - Recursively traverse the left subtree.
//     - Recursively traverse the right subtree.
// If the current node is null,
// return from the recursive call.
// Return the preorder traversal list.

// Time Complexity: O(n)
// Space Complexity: O(n) // recursion stack + result


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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    private void preOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        
        res.add(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }
}
