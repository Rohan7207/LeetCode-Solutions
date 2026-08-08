// Problem: Construct Binary Search Tree From Preorder Traversal
// Link: https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
// Difficulty: Medium

// Approach:
// The preorder traversal always visits:
// root → left subtree → right subtree.
//
// Since the given traversal belongs to a BST, we can use the BST
// property to determine where each value belongs.
//
// Step 1:
// Maintain a global `index` pointing to the next unused value in
// the preorder array.
//
// Step 2:
// Start building the tree with `Integer.MAX_VALUE` as the bound
// because the root initially has no upper restriction.
//
// Step 3:
// For the current value:
// - If all values are processed, return null.
// - If preorder[index] > bound, this value cannot belong to the
//   current subtree, so return null.
// - Otherwise, create a node using preorder[index] and move index.
//
// Step 4:
// Build the left subtree using `root.val` as the new bound.
//
// This is because every value in the left subtree must be smaller
// than the current root.
//
// Step 5:
// Build the right subtree using the previous `bound`.
//
// The right subtree must contain values greater than the current
// root, but it must still respect the upper bound inherited from
// the parent.
//
// Example:
//
//        8
//       / \
//      5   10
//
// For 8:
// left  → values must be < 8
// right → values must be > 8 but still <= parent's bound.
//
// The preorder index is shared across all recursive calls, so each
// value is processed exactly once.

// Time Complexity:
// O(n)
//
// Space Complexity:
// O(h)
// where h is the height of the constructed BST due to recursion.


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

    private int index = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder, Integer.MAX_VALUE);
    }

    private TreeNode build(int[] preorder, int bound) {
        if (index == preorder.length || preorder[index] > bound) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[index++]);

        root.left = build(preorder, root.val); // new restriction
        root.right = build(preorder, bound); // keep parent's restriction

        return root;
    }
}
