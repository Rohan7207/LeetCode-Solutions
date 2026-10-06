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

    private int count = 1;

    public int goodNodes(TreeNode root) {
        dfs(root, root.val);

        return count;
    }

    private void dfs(TreeNode root, int max) {
        if(root == null) {
            return;
        }

        max = Math.max(max, root.val);

        if(root.left != null && root.left.val >= max) {
            count++;
            dfs(root.left, max);
        } else {
            dfs(root.left, max);
        }

        if(root.right != null && root.right.val >= max) {
            count++;
            dfs(root.right, max);
        } else {
            dfs(root.right, max);
        }
    }
}