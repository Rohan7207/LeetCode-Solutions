// Problem: Second Minimum Node In a Binary Tree
// Link: https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
// Difficulty: Easy

// Approach:
// In this special binary tree:
//     root.val is always the minimum value
//     in the entire tree.
// So we need to find the smallest value
// that is greater than root.val.
// Store:
//     minValue  -> root.val
//     secondMin -> smallest candidate greater than minValue
// Use DFS traversal.
// For every node:
//     - If node is null:
//           return
//     - If node.val > minValue:
//           node.val is a candidate for second minimum
//           update secondMin with minimum value
//           stop exploring this branch
//     - If node.val == minValue:
//           continue DFS on left and right children
// After DFS:
//     - If secondMin was never updated:
//           return -1
//     - Otherwise:
//           return secondMin

// Time Complexity: O(n)
// Space Complexity: O(h)


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
