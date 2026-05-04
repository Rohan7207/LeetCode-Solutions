// Problem: Convert Sorted Array to Binary Search Tree
// Link: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
// Difficulty: Easy

// Approach:
// Use recursion to build a height-balanced BST
// from a sorted array.
// For each subarray:
//     - Pick the middle element as the root
//       to maintain balance.
//     - Recursively build the left subtree
//       from the left half of the array.
//     - Recursively build the right subtree
//       from the right half of the array.
// Base case:
//     - If left > right, return null.
// Return the constructed root node.

// Time Complexity: O(n)
// Space Complexity: O(n) // recursion stack


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
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0) return null;

        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int left, int right) {
        if(left > right) return null;

        int mid = left + (right - left) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTree(nums, left, mid - 1);
        root.right = buildTree(nums, mid + 1, right);

        return root;
    }
}
// 4. Recursively build the right subtree using the right half

