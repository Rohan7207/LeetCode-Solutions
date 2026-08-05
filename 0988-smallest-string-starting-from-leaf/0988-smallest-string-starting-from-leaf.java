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

    private String ans = null;
    private StringBuilder sb = new StringBuilder();

    public String smallestFromLeaf(TreeNode root) {
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if(root == null) {
            return;
        }

        sb.append((char) (root.val + 'a'));

        if(root.left == null && root.right == null) {
            String curr = sb.reverse().toString();
            // Java strings are compared lexicographically.
            if(ans == null || curr.compareTo(ans) < 0) {
                ans = curr;
            }

            sb.reverse();
        }

        dfs(root.left);
        dfs(root.right);

        sb.deleteCharAt(sb.length() - 1);
    }
}