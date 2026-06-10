// Problem: Subtree if Another Tree
// Link: https://leetcode.com/problems/subtree-of-another-tree/
// Difficulty: Easy

// Approach:
// Check whether subRoot exists as a subtree
// inside the main tree root.
// For every node in the main tree:
//     - Treat that node as a possible starting point
//       of the subtree.
//     - Check if the tree starting from this node
//       is identical to subRoot.
// If identical:
//     - Return true
// Otherwise:
//     - Recursively check in left subtree
//     - Recursively check in right subtree
// To check identical trees:
//     - If both nodes are null:
//           return true
//     - If only one node is null:
//           return false
//     - If values are different:
//           return false
//     - If values are same:
//           check left subtree
//           check right subtree
// If any node in root matches subRoot completely,
// return true.

// Time Complexity: O(n * m)
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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        
        if (isidentical(root, subRoot)) return true;
        
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isidentical(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }

        if (root == null || subRoot == null) {
            return false;
        }

        if (root.val == subRoot.val) {
            return isidentical(root.left, subRoot.left) && isidentical(root.right, subRoot.right);
        }
        
        return false;
    }
}
