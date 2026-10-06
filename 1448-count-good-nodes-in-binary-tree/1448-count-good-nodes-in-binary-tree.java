// Problem: Count Good Nodes in Binary Tree
// Link: https://leetcode.com/problems/count-good-nodes-in-binary-tree/
// Difficulty: Medium

// Approach:
// 1. Start DFS from root with root.val as the maximum seen so far.
// 2. At each node, update max = max(max, root.val).
// 3. Check each child against this updated path maximum.
// 4. If child.val >= max, the child is a good node → count++.
// 5. Recursively explore left and right using the updated max.
// 6. Since `max` is passed as a parameter, each root-to-node path
//    maintains its own maximum.

// Time Complexity: O(n)
// Space Complexity: O(h), where h is the tree height.


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

    private int count = 1;

    public int goodNodes(TreeNode root) {
        dfs(root, root.val);

        return count;
    }

    private void dfs(TreeNode root, int max) {
        if (root == null) {
            return;
        }

        max = Math.max(max, root.val);

        if (root.left != null && root.left.val >= max) {
            count++;
        }

        if (root.right != null && root.right.val >= max) {
            count++;
        }

        dfs(root.left, max);
        dfs(root.right, max);
    }
}
