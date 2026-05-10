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
    public int sumNumbers(TreeNode root) {
        // Start the DFS with an initial sum of 0
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int currSum) {
        // Base case: if we hit a null child, it contributes nothing to the sum
        if(root == null) return 0;

        // 1. Update the current number (shift left by 10 and add current digit)
        currSum = (currSum * 10) + root.val;

        // 2. Check if we are at a leaf node
        if(root.left == null && root.right == null) {
            return currSum;
        }

        // 3. Recursively find sums from left and right subtrees
        int leftSum = dfs(root.left, currSum);
        int rightSum = dfs(root.right, currSum);

        // 4. Return the total sum found in both branches
        return leftSum + rightSum;
    }
}