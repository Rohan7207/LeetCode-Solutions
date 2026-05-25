// Problem: kth Smallest Element in a BST
// Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
// Difficulty: Medium

// Approach:
// Perform inorder traversal of the BST
// because inorder visits nodes in sorted order.
// Before processing a node,
// check whether kth element has already
// been found using: i >= k
// If found, stop further recursion.
// Traverse left subtree first.
// After returning:
//     - Increment visited node count.
//     - If count becomes k,
//       store current node value as answer.
// Then traverse right subtree only if
// answer has not been found yet.
// Return the stored answer.

// Time Complexity:
//     O(k) average
//     O(n) worst case
//
// Space Complexity: O(h) recursion stack


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
    int i = 0;
    int ans = 0;
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return ans;

        kthSmallest(root.left, k);
        i++;

        if(i == k) 
            ans = root.val;

        kthSmallest(root.right, k);

        return ans;
    }
}
