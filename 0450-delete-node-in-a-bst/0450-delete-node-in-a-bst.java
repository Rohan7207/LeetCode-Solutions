// Problem : Delete Node in a BST
// Link : https://leetcode.com/problems/delete-node-in-a-bst/
// Difficulty : Medium

// Approach:
// Delete a node from a Binary Search Tree.
// Use BST property to search for the key:
//     If key < root.val:
//         go to left subtree
//     If key > root.val:
//         go to right subtree
//     If key == root.val:
//         node to delete is found
// There are 3 deletion cases:
// Case 1: Node has no child
//     - Return null
//     - This removes the node from the tree
// Case 2: Node has one child
//     - Return the existing child
//     - Parent will directly connect to that child
// Case 3: Node has two children
//     - Find inorder successor
//     - Inorder successor is the smallest node
//       in the right subtree
//     - Copy successor value into current node
//     - Delete successor node from right subtree
// Finally return root after updating links.

// Time Complexity: O(h)
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            }

            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            TreeNode IS = inordersuccessor(root.right);
            root.val = IS.val;
            root.right = deleteNode(root.right, IS.val);
        }

        return root;
    }

    private TreeNode inordersuccessor(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        
        return root;
    }
}
