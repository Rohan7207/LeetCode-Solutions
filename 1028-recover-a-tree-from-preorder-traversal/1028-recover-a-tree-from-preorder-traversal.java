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

    private int index = 0;

    public TreeNode recoverFromPreorder(String traversal) {
        return dfs(traversal, 0);
    }

    private TreeNode dfs(String traversal, int depth) {
        if(index > traversal.length()) {
            return null;
        }

        // Count the number of dashes
        int dashCount = 0;
        while((index + dashCount) < traversal.length() && traversal.charAt(index + dashCount) == '-') {
            dashCount++;
        }

        // If the number of dashes doesn't match the current depth, return null
        if(dashCount != depth) return null;

        // Move index past the dashes
        index += dashCount;

        // Extract the node value
        int value = 0;
        while(index < traversal.length() && Character.isDigit(traversal.charAt(index))) {
            value = value * 10 + (traversal.charAt(index++) - '0');
        }

        // Create the current node
        TreeNode root = new TreeNode(value);

        // Recursively build the left and right subtrees
        root.left = dfs(traversal, depth + 1);
        root.right = dfs(traversal, depth + 1);

        return root;
    }
}