// Problem: Minimum Absolute Difference in BST
// Link: https://leetcode.com/problems/minimum-absolute-difference-in-bst/
// Difficulty: Easy

// Approach:
// Inorder traversal of a BST produces
// values in sorted order.
// In a sorted sequence, the minimum difference
// between any two values will always be found
// between two consecutive elements.
// Maintain:
//     - prev -> previously visited node value
//     - min  -> minimum difference found so far
// Perform inorder traversal:
//     1. Traverse left subtree
//     2. Process current node
//          - If prev exists:
//                calculate difference:
//                    root.val - prev
//                update minimum difference
//          - Update prev to current value
//     3. Traverse right subtree
// Since inorder traversal visits nodes in
// ascending order, comparing only adjacent
// values is sufficient.
// Finally return the minimum difference.

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
    int min = Integer.MAX_VALUE;
    Integer prev = null;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;

        getMinimumDifference(root.left);

        if (prev != null) {
            min = Math.min(min, root.val - prev);
        }
        prev = root.val;

        getMinimumDifference(root.right);

        return min;
    }
}
