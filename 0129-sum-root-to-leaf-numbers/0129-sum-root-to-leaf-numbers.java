// Problem: Sum Root to Leaf Numbers
// Link: https://leetcode.com/problems/sum-root-to-leaf-numbers/
// Difficulty: Medium

// Approach:
// Use DFS traversal to build the number
// formed from root to current node.
// At each node:
//     - Update current number by:
//           currSum = currSum * 10 + root.val
//     - If current node is a leaf:
//           return the formed number.
//     - Recursively calculate left and right subtree sums.
// Return the sum of all root-to-leaf numbers.

// Time Complexity: O(n)
// Space Complexity: O(h)
//
// n = number of nodes
// h = height of tree


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
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int currSum) {
        if (root == null) return 0;

        currSum = (currSum * 10) + root.val;

        if (root.left == null && root.right == null) {
            return currSum;
        }

        int leftSum = dfs(root.left, currSum);
        int rightSum = dfs(root.right, currSum);

        return leftSum + rightSum;
    }
}
