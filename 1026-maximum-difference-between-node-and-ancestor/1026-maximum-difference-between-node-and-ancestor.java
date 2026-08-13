// Problem: Maximum Difference Between Node and Ancestor
// Link: https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
// Difficulty: Medium

// Approach:
// Use DFS and maintain the minimum and maximum values seen
// on the current root-to-node path.
//
// 1. Start DFS from the root with:
//       min = root.val
//       max = root.val
//
// 2. At every node, update `min` and `max` using the current
//    node's value.
//
// 3. Since `min` and `max` represent the smallest and largest
//    values among the current node and its ancestors, the maximum
//    possible ancestor difference on this path is:
//
//       max - min
//
// 4. Update the global answer using this difference.
//
// 5. Pass the updated `min` and `max` to both children because
//    they will have the same ancestors plus the current node.
//
// This avoids calculating the minimum and maximum of every
// subtree separately.

// Time Complexity: O(n)
//
// Space Complexity: O(h)
// where h is the height of the tree due to the DFS recursion.


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

    private int ans = 0;

    public int maxAncestorDiff(TreeNode root) {
        dfs(root, root.val, root.val);
        return ans;
    }

    private void dfs(TreeNode root, int min, int max) {
        if (root == null) {
            return;
        }

        // Include the current node in the path
        min = Math.min(min, root.val);
        max = Math.max(max, root.val);

        // The maximum difference between any ancestor/current node
        ans = Math.max(ans, max - min);

        dfs(root.left, min, max);
        dfs(root.right, min, max);
    }
}
