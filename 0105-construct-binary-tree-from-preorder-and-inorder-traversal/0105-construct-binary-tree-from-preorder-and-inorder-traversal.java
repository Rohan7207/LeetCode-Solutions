// Problem: Construct Binary Tree from Preorder and Inorder Traversal
// Link: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
// Difficulty: Medium

// Approach:
// Use recursion to build the tree using preorder
// and inorder traversals.
// Preorder gives the root node (first element).
// Use a hashmap to store index of each value
// in inorder for O(1) lookup.
// For each recursive call:
//     - Pick current root from preorder using preidx.
//     - Find its index in inorder.
//     - Elements to the left of index form left subtree.
//     - Elements to the right form right subtree.
// Recursively build:
//     - Left subtree first
//     - Then right subtree
// Return the constructed tree.

// Time Complexity: O(n)
// Space Complexity: O(n)


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
    int preidx;
    Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preidx = 0;
        inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return Tree(preorder, 0, preorder.length - 1);
    }

    
    private TreeNode Tree(int[] preorder, int left, int right) {
        if (left > right) return null;

        int rootval = preorder[preidx];
        preidx++;

        TreeNode root = new TreeNode(rootval);
        root.left = Tree(preorder, left, inorderMap.get(rootval) - 1);
        root.right = Tree(preorder, inorderMap.get(rootval) + 1, right);

        return root;
    }
}
