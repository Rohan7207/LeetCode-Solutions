// Problem: Merge Two Binary Trees
// Link: https://leetcode.com/problems/merge-two-binary-trees/
// Difficulty: Easy

// Approach:
// Merge two binary trees.
// If both nodes exist:
//     - Create a new node
//     - Its value will be:
//           root1.val + root2.val
//     - Recursively merge left subtrees
//     - Recursively merge right subtrees
// If only one node exists:
//     - Return that node directly
// If both nodes are null:
//     - Return null
// This builds a merged tree where overlapping
// nodes are added together, and non-overlapping
// nodes are kept as they are.

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
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return null;

        if(root1 == null) return root2;
        if(root2 == null) return root1;

        TreeNode mergeRoot = new TreeNode();
        mergeRoot.val = root1.val + root2.val;

        mergeRoot.left = mergeTrees(root1.left, root2.left);
        mergeRoot.right = mergeTrees(root1.right, root2.right);

        return mergeRoot;
    }
}
