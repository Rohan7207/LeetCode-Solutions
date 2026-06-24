// Problem: Range Sum of BST
// Link: https://leetcode.com/problems/range-sum-of-bst/
// Difficulty: Easy

// Approach:
// We need to find the sum of all node values in the BST
// that lie between low and high.
// Since it is a Binary Search Tree:
//     left subtree values  < root.val
//     right subtree values > root.val
// We can use this property to avoid visiting unnecessary nodes.
// If root is null:
//     return 0
// If root.val is between low and high:
//     add root.val
//     search both left and right subtree
// If root.val is greater than or equal to low:
//     possible valid values may exist in the left subtree
//     so search left
// If root.val is less than low:
//     left subtree will also be smaller,
//     so skip left and search right
// Finally return the total sum.

// Time Complexity: O(n) in worst case
// Space Complexity: O(h)
//     h = height of tree due to recursion stack


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
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;

        int sum = 0;
        if (root.val >= low && root.val <= high) {
            sum += root.val;
            sum += rangeSumBST(root.left, low, high);
            sum += rangeSumBST(root.right, low, high);
        } else if (root.val >= low) {
            sum += rangeSumBST(root.left, low, high);
        } else {
            sum += rangeSumBST(root.right, low, high);
        }

        return sum;
    }
}
