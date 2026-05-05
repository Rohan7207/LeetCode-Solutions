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
    public boolean isBalanced(TreeNode root) {
        return check(root) != -1;
    }

    private int check(TreeNode node) {
        if (node == null) return 0;

        int left = check(node.left);
        if (left == -1) return -1; // left subtree unbalanced

        int right = check(node.right);
        if (right == -1) return -1; // right subtree unbalanced

        if (Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }
}

//For balanced tree the diff of height of left and right subtree must be 1 or 0

// O(n^2 solution)
/* 
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        int leftheight = getheight(root.left);
        int rightheight = getheight(root.right);

        if (Math.abs(leftheight - rightheight) > 1) return false;

        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int getheight(TreeNode node) {
        if (node == null) return 0;

        int left = getheight(node.left);
        int right = getheight(node.right);

        return Math.max(left, right) + 1;
    }
*/
