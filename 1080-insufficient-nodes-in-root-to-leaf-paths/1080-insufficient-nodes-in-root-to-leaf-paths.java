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
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        return dfs(root, limit, 0);
    }

    private TreeNode dfs(TreeNode root, int limit, int sum) {
        if(root == null) {
            return null;
        }

        sum += root.val;

        // Leaf node
        if(root.left == null && root.right == null) {
            return sum < limit ? null : root;
        }

        root.left = dfs(root.left, limit, sum);
        root.right = dfs(root.right, limit, sum);

        // Both paths through this node were insufficient
        if(root.left == null && root.right == null) {
            return null;
        }

        return root;
    }
}