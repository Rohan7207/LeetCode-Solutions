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
    long secondMin = Long.MAX_VALUE;

    public int findSecondMinimumValue(TreeNode root) {
        dfs(root, root.val);

        if (secondMin == Long.MAX_VALUE)
            return -1;
        else
            return (int) secondMin;
    }

    private void dfs(TreeNode node, int minValue) {
        if (node == null)
            return;

        if (node.val > minValue) {
            secondMin = Math.min(secondMin, node.val);
            return;
        }

        if (node.val == minValue) {
            dfs(node.left, minValue);
            dfs(node.right, minValue);
        }
    }
}