// Problem: Construct Binary Tree from Inorder and Postorder Traversal
// Link: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
// Difficulty: Medium

// Approach:
// Use recursion to build the tree using
// inorder and postorder traversals.
// Postorder gives the root node (last element).
// Maintain an index (postIdx) starting from
// postorder.length - 1.
// Use a hashmap to store index of each value
// in inorder for O(1) lookup.
// For each recursive call:
//     - Pick root from postorder using postIdx.
//     - Decrement postIdx.
//     - Find root index in inorder.
//     - Elements to the right form right subtree.
//     - Elements to the left form left subtree.
// IMPORTANT:
//     - Build right subtree first,
//       then left subtree (reverse of preorder case).
// If start > end, return null.
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
    int postidx;
    Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postidx = postorder.length - 1;
        inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return Tree(postorder, 0, postorder.length - 1);
    }

    private TreeNode Tree(int[] postorder, int left, int right) {
        if (left > right)
            return null;

        int rootVal = postorder[postidx];
        postidx--;

        TreeNode root = new TreeNode(rootVal);
        root.right = Tree(postorder, inorderMap.get(rootVal) + 1, right);
        root.left = Tree(postorder, left, inorderMap.get(rootVal) - 1);

        return root;
    }
}
