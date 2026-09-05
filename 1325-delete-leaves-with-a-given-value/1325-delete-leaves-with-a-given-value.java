// Problem: Delete Leaves With a Given Value
// Link: https://leetcode.com/problems/delete-leaves-with-a-given-value/
// Difficulty: Medium

// Approach:
// Use Postorder DFS.
//
// 1. Recursively process the left and right subtrees first.
//
// 2. Assign the returned nodes back to root.left and root.right,
//    because a child may have been deleted during recursion.
//
// 3. After processing both children, check whether the current
//    node has become a leaf and its value equals target.
//
// 4. If it is a target leaf, return null to delete it.
//
// 5. Otherwise, return the current root.

// Time Complexity: O(n)
// Space Complexity: O(h)
// where h is the height of the tree.


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
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }

        return root;
    }
}
