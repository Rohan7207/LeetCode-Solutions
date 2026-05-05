// Problem: Path Sum
// Link: https://leetcode.com/problems/path-sum/
// Difficulty: Easy

// Approach:
// Use recursion (DFS) to check all root-to-leaf paths.
// For each node:
//     - Subtract the node's value from targetSum.
//     - If it's a leaf node:
//         - Check if remaining sum equals node value.
//         - If yes → return true.
//     - Otherwise recursively check:
//         - left subtree
//         - right subtree
// If any path returns true, return true.
// Else return false.

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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        // Check if we are at a leaf and the value matches the remaining sum
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        return hasPathSum(root.left, targetSum - root.val) ||
                hasPathSum(root.right, targetSum - root.val);
    }
}
