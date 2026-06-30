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
    TreeNode prev = null;
    TreeNode newRoot = null;

    public TreeNode increasingBST(TreeNode root) {
        dfs(root);

        return newRoot;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.left);

        if (newRoot == null) {
            newRoot = root;
        }

        if (prev != null) {
            prev.right = root;
        }

        root.left = null;

        prev = root;

        dfs(root.right);
    }
}