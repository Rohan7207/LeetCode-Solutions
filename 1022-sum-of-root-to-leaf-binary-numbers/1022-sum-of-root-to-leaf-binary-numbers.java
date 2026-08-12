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

    private int ans =  0;

    public int sumRootToLeaf(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode root, int current) {
        if(root == null) {
            return;
        }

        current = current * 2 + root.val;

        if(root.left == null && root.right == null) {
            ans += current;
            return;
        }

        dfs(root.left, current);
        dfs(root.right, current);
    }
}