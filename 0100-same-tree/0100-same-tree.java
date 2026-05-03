// Problem: Same Tree
// Link: https://leetcode.com/problems/same-tree/
// Difficulty: Easy

// Approach:
// Use recursion to compare both trees node by node.
//     - If both nodes are null,
//       the trees are identical at this branch.
//     - If one node is null and the other is not,
//       trees are different.
//     - Check whether current node values are equal.
// Recursively compare:
//     - left subtrees
//     - right subtrees
// Return true only if all nodes and structures match.

// Time Complexity: O(n)
// Space Complexity: O(n) // recursion stack


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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
