// Problem: kth Smallest Element in a BST
// Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
// Difficulty: Medium

// Approach:
// Use inorder traversal because inorder
// traversal of a BST visits nodes in
// ascending sorted order.
// Maintain a counter to track how many
// nodes have been visited.
// Traverse:
//     Left → Root → Right
// Before each recursive call,
// check whether the kth element has
// already been found.
// When visiting a node:
//     - Increment the counter.
//     - If counter becomes k,
//       store the current node value
//       as the answer and stop recursion.
// Use early termination to avoid
// traversing unnecessary nodes after
// finding the answer.

// Time Complexity:
//     O(k) average
//     O(n) worst case
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
        if(root == null || i >= k) return ans;

        kthSmallest(root.left, k);
        i++;

        if(i == k) {
            ans = root.val;
            return ans;
        }

        kthSmallest(root.right, k);

        return ans;
    }
}
