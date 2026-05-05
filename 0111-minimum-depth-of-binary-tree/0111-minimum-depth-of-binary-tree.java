// Problem: Minimum Depth of Binary Tree
// Link: https://leetcode.com/problems/minimum-depth-of-binary-tree/
// Difficulty: Easy

// Approach:
// Use Breadth First Search (BFS)
// to find the minimum depth.
// Start from root and traverse level by level
// using a queue.
// For each level:
//     - Traverse all nodes at that level.
//     - If a node is a leaf (no left and right child),
//       return current depth immediately.
//     - Otherwise add its children to the queue.
// Increment depth after finishing each level.
// BFS guarantees the first leaf encountered
// is at minimum depth.

// Time Complexity: O(n)
// Space Complexity: O(n)


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
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;

        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = q.poll();

                if (current.left == null && current.right == null) {
                    return depth;
                }

                if (current.left != null) q.offer(current.left);
                if (current.right != null) q.offer(current.right);
            }

            depth++;
        }

        return depth;
    }
}
