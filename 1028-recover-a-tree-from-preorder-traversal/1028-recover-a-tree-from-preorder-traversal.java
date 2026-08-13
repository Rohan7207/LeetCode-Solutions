// Problem: Recover a Tree From Preorder Traversal
// Link: https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/
// Difficulty: Hard

// Approach:
// Use DFS with a global `index` to reconstruct the tree directly
// from the preorder traversal string.
//
// The traversal format tells us the depth of every node using dashes:
//
//     1-2--3--4-5--6--7
//
// For every node:
//
// 1. Count the number of consecutive dashes starting from `index`.
//    This tells us the depth of the next node.
//
// 2. If the number of dashes is not equal to the depth expected by
//    the current recursive call, this node does not belong to this
//    subtree. Return null without changing `index`.
//
// 3. Move `index` past the dashes.
//
// 4. Read all consecutive digits to construct the node's value.
//    `value = value * 10 + digit` handles multi-digit values.
//
// 5. Create the current node.
//
// 6. Recursively build its left and right children with
//    `depth + 1`.
//
// The global `index` ensures that we process the traversal string
// from left to right exactly once.

// Time Complexity: O(n)
//
// Space Complexity: O(h)
// where h is the height of the reconstructed tree.


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

    public TreeNode recoverFromPreorder(String traversal) {
        return dfs(traversal, 0);
    }

    // Brute Force (Recursive with String Manipulation)
    private TreeNode dfs(String traversal, int depth) {
        if (index > traversal.length()) {
            return null;
        }

        // Count the number of dashes
        int dashCount = 0;
        while ((index + dashCount) < traversal.length() && traversal.charAt(index + dashCount) == '-') {
            dashCount++;
        }

        // If the number of dashes doesn't match the current depth, return null
        if (dashCount != depth)
            return null;

        // Move index past the dashes
        index += dashCount;

        // Extract the node value
        int value = 0;
        while (index < traversal.length() && Character.isDigit(traversal.charAt(index))) {
            value = value * 10 + (traversal.charAt(index++) - '0');
        }

        // Create the current node
        TreeNode root = new TreeNode(value);

        // Recursively build the left and right subtrees
        root.left = dfs(traversal, depth + 1);
        root.right = dfs(traversal, depth + 1);

        return root;
    }
}
