// Problem: Deepest Leaves Sum
// Link: https://leetcode.com/problems/deepest-leaves-sum/
// Difficulty: Medium

// Approach:
// Use DFS + Maximum Depth.
//
// 1. First find the maximum depth of the binary tree using DFS.
//
// 2. The deepest leaves are exactly the nodes whose depth equals
//    the maximum depth.
//
// 3. Traverse the tree again using `helper()`.
//
// 4. Pass the remaining height while moving downward:
//
//      left/right → height - 1
//
// 5. When `height == 1`, the current node is at the deepest level,
//    so add its value to `ans`.
//
// 6. Return the accumulated sum.

// Time Complexity: O(n)
// Space Complexity: O(h)
// where h is the height of the tree.


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

    int ans = 0;

    public int deepestLeavesSum(TreeNode root) {
        int height = maxDepth(root);

        helper(root, height);
        return ans;
    }

    private void helper(TreeNode root, int height) {
        if (root == null) {
            return;
        }

        if (height == 1) {
            ans += root.val;
        }

        helper(root.left, height - 1);
        helper(root.right, height - 1);
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int h1 = maxDepth(root.left);
        int h2 = maxDepth(root.right);

        return Math.max(h1, h2) + 1;
    }
}
