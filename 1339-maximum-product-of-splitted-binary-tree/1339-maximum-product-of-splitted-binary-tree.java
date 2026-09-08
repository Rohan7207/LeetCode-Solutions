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
    long totalSum = 0;
    long maxProduct = 0;
    int MOD = 1000000007;

    public int maxProduct(TreeNode root) {
        sumOfTree(root);
        dfs(root);

        return (int) (maxProduct % MOD);
    }

    private long dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        long left = dfs(root.left);
        long right = dfs(root.right);

        long currSum = root.val + left + right;
        long product = (totalSum - currSum) * currSum;

        if (product > maxProduct) {
            maxProduct = product;
        }

        return currSum;
    }

    private void sumOfTree(TreeNode root) {
        if (root == null) {
            return;
        }

        totalSum += root.val;
        sumOfTree(root.left);
        sumOfTree(root.right);
    }
}