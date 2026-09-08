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
    long sum = 0;
    long product = 1;
    int MOD = 1000000007;

    public int maxProduct(TreeNode root) {
        sumOfTree(root);
        dfs(root);

        return (int) (product % MOD);
    }

    private long dfs(TreeNode root) {
        if(root == null) {
            return 0;
        }

        long left = dfs(root.left);
        long right = dfs(root.right);

        long currSum = root.val + left + right;
        product = Math.max(product, (sum - currSum) * currSum);

        return currSum;
    }

    private void sumOfTree(TreeNode root) {
        if(root == null) {
            return;
        }

        sum += root.val;
        sumOfTree(root.left);
        sumOfTree(root.right);
    }
}