// Problem : Insufficient Nodes in Root to Leaf Paths
// Link : https://leetcode.com/problems/insufficient-nodes-in-root-to-leaf-paths/
// Difficulty : Medium

// Approach:
// Use DFS to examine every root-to-leaf path and prune paths whose
// total sum is less than the given limit.
//
// 1. Keep the sum of values from the root to the current node.
//
// 2. When a leaf is reached:
//    - If the root-to-leaf sum is less than `limit`, remove the leaf
//      by returning null.
//    - Otherwise, keep it.
//
// 3. For an internal node, recursively process both its left and
//    right subtrees.
//
// 4. After processing both children, if both became null, it means
//    no valid root-to-leaf path passes through this node, so remove
//    the node as well.
//
// 5. Otherwise, keep the node.

// Time Complexity: O(n)
//
// Space Complexity: O(h)
// where h is the height of the tree due to recursion.

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
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        return dfs(root, limit, 0);
    }

    private TreeNode dfs(TreeNode root, int limit, int sum) {
        if (root == null) {
            return null;
        }

        sum += root.val;

        // Leaf node
        if (root.left == null && root.right == null) {
            return sum < limit ? null : root;
        }

        root.left = dfs(root.left, limit, sum);
        root.right = dfs(root.right, limit, sum);

        // Both paths through this node were insufficient
        if (root.left == null && root.right == null) {
            return null;
        }

        return root;
    }
}
