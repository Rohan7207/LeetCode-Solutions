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
    public boolean isUnivalTree(TreeNode root) {
        if(root == null) {
            return true;
        }

        return helper(root, root.val);
    }

    private boolean helper(TreeNode root, int val) {
        if(root == null) {
            return true;
        }

        if(root.val != val) {
            return false;
        }

        return helper(root.left, val) && helper(root.right, val);
    }
}

/*
     boolean leftCorrect = (root.left == null ||
                (root.val == root.left.val && isUnivalTree(root.left)));

        boolean rightCorrect = (root.right == null ||
                (root.val == root.right.val && isUnivalTree(root.right)));

        return leftCorrect && rightCorrect;
*/