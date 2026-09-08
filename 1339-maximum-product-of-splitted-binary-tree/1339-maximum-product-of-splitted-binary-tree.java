// Problem: Maximum Product of Splitted Binary Tree
// Link: https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/
// Difficulty: Medium

// Approach:
// Use Two-Pass Tree DFS + Subtree Sum + Greedy Maximum.
//
// 1. First traverse the entire tree and calculate the total sum
//    of all nodes.
//
// 2. Perform a second DFS. For every node, calculate the sum of
//    its complete subtree:
//
//      currSum = node.val + leftSubtreeSum + rightSubtreeSum
//
// 3. Cutting the edge above this subtree divides the tree into
//    two parts:
//
//      Part 1 = currSum
//      Part 2 = totalSum - currSum
//
// 4. Calculate the product of these two parts and keep the maximum.
//
// 5. Use long because both the tree sum and the product can exceed
//    the int range.
//
// 6. Apply MOD only to the final maximum product.

// Time Complexity: O(n)
// Space Complexity: O(h)
// where h is the tree height due to recursion.


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
    long totalSum = 0;
    long maxProduct = 0;
    int MOD = 1000000007;

    public int maxProduct(TreeNode root) {
        sumOfTree(root);
        dfs(root);

        return (int) (maxProduct % MOD);
    }

    private long dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        long left = dfs(root.left);
        long right = dfs(root.right);

        long currSum = root.val + left + right;
        long product = (totalSum - currSum) * currSum;

        if (product > maxProduct) {
            maxProduct = product;
        }

        return currSum;
    }

    private void sumOfTree(TreeNode root) {
        if (root == null) {
            return;
        }

        totalSum += root.val;
        sumOfTree(root.left);
        sumOfTree(root.right);
    }
}
