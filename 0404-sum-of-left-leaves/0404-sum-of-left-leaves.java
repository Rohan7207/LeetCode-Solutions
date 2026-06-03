// Problem: Sum of Left Leaves
// Link: https://leetcode.com/problems/sum-of-left-leaves/
// Difficulty: Easy

// Approach:
// Use recursion to traverse the binary tree.
// For every node, check whether its left child
// exists and is a leaf node.
// A leaf node has:
//     left == null
//     right == null
// If the left child is a leaf, add its value to sum.
// Then recursively calculate the sum of
// left leaves in both left and right subtrees.
// Return the total sum.

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
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        
        int sum = 0;

        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }

        sum += sumOfLeftLeaves(root.left);
        sum += sumOfLeftLeaves(root.right);

        return sum;
    }
}
