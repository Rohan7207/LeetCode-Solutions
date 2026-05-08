// Problem: Binary Tree Maximum Path Sum
// Link: https://leetcode.com/problems/binary-tree-maximum-path-sum/
// Difficulty: Hard

// Approach:
// Use postorder DFS traversal to calculate
// the maximum path sum.
// For each node:
//     - Recursively calculate maximum contribution
//       from left and right subtrees.
//     - Ignore negative paths using max(sum, 0).
//     - Update global maximum answer considering:
//           root + left contribution + right contribution
//       since path can pass through current node.
//     - Return:
//           root + max(left, right)
//       because parent can continue only one path.
// Continue recursively for all nodes.
// Return the global maximum answer.

// Time Complexity: O(n)
// Space Complexity: O(n)


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
    private int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if(root == null) return 0;

        int leftSum = Math.max(dfs(root.left), 0);
        int rightSum = Math.max(dfs(root.right), 0);

        ans = Math.max(ans, root.val + leftSum + rightSum);

        return root.val + Math.max(leftSum, rightSum);
    }
}
