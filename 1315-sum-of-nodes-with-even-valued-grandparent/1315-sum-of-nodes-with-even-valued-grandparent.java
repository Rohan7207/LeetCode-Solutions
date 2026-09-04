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

    int sum = 0;

    public int sumEvenGrandparent(TreeNode root) {
        dfs(root, 1, 1);

        return sum;
    }

    private void dfs(TreeNode node, int parent, int grandParent) {
        if (node == null) {
            return;
        }

        if (grandParent % 2 == 0) {
            sum += node.val;
        }

        dfs(node.left, node.val, parent);
        dfs(node.right, node.val, parent);
    }
}