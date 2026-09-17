/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {

    private TreeNode ans;
    private TreeNode target;

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        this.target = target;
        inOrder(original, cloned);

        return ans;
    }

    private void inOrder(TreeNode root, TreeNode clonedRoot) {
        if(root != null) {
            inOrder(root.left, clonedRoot.left);

            if(root == target) {
                ans = clonedRoot;
            }

            inOrder(root.right, clonedRoot.right);
        }
    }
}