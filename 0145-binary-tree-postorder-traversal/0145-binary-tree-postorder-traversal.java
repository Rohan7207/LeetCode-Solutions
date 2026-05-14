// Problem: Binary Tree Postorder Traversal
// Link: https://leetcode.com/problems/binary-tree-postorder-traversal/
// Difficulty: Easy

// Approach:
// Use recursion to perform postorder traversal
// of the binary tree.
// Create a helper function:
//     - Recursively traverse the left subtree.
//     - Recursively traverse the right subtree.
//     - Add the current root value to result.
// If the current node is null,
// return from the recursive call.
// Return the postorder traversal list.

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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }

    private void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;

        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.val);
    }
}
