// Problem: Find a Corresponding Node of a Binary Tree in a Clone of That Tree
// Link: https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
// Difficulty: Easy

// Approach:
// Use Synchronized Inorder Traversal.
//
// 1. Traverse both trees simultaneously using the original tree as
//    the reference and the cloned tree to locate the corresponding node.
//
// 2. Visit the left subtree recursively.
//
// 3. When root == target, store the corresponding clonedRoot in ans.
//
// 4. Visit the right subtree recursively.
//
// 5. Return the cloned node stored in ans.

// Time Complexity: O(n)
// Space Complexity: O(h)


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {

    private TreeNode ans;
    private TreeNode target;

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        this.target = target;
        inOrder(original, cloned);

        return ans;
    }

    private void inOrder(TreeNode root, TreeNode clonedRoot) {
        if(root != null) {
            inOrder(root.left, clonedRoot.left);

            if(root == target) {
                ans = clonedRoot;
            }

            inOrder(root.right, clonedRoot.right);
        }
    }
}
