// Problem: Leaf Similar Trees
// Link: https://leetcode.com/problems/leaf-similar-trees/
// Difficulty: Easy

// Approach:
// Two binary trees are leaf-similar if their leaf nodes appear
// in the same order and have the same values.
// Use DFS traversal to extract the leaf sequence of both trees.
// Step 1:
// Create two lists:
//     list1 -> stores leaves of root1
//     list2 -> stores leaves of root2
// Step 2:
// Perform DFS on both trees.
// During DFS:
//     If node is null:
//          return
//     If node has no left and right child:
//          It is a leaf node
//          Store its value in the list
//     Otherwise:
//          Traverse left subtree
//          Traverse right subtree
// This maintains the left-to-right order of leaf nodes.
// Step 3:
// Compare both lists.
// If both leaf sequences are equal,
// trees are leaf-similar.

// Time Complexity: O(n + m)
//     n = nodes in first tree
//     m = nodes in second tree
//
// Space Complexity: O(n + m)
//     for storing leaf sequences


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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        dfs(root1, list1);
        dfs(root2, list2);

        return list1.equals(list2);
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }

        dfs(root.left, list);
        dfs(root.right, list);
    }
}
