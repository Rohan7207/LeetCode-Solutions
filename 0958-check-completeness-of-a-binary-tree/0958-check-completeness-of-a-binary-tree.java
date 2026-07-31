// Problem: Check Completeness of a Binary Tree
// Link: https://leetcode.com/problems/check-completeness-of-a-binary-tree/
// Difficulty: Medium

// Approach:
// A complete binary tree is filled level by level from left to right.
//
// Perform a level-order traversal (BFS) using a queue.
//
// During traversal:
//
// 1. Start by adding the root to the queue.
// 2. Maintain a boolean `seenNull`.
// 3. For every node removed from the queue:
//
//    - If the node is null, mark `seenNull = true`.
//      This means we've encountered the first missing position.
//
//    - Otherwise, if a non-null node appears after a null has already
//      been seen, the tree cannot be complete, so return false.
//
//    - If the node is not null and no null has been seen yet,
//      enqueue both its left and right children (including nulls).
//
// If the traversal finishes without finding a non-null node after a null,
// the tree satisfies the definition of a complete binary tree.

// Time Complexity:
// O(n)
// (Every node is processed once.)
//
// Space Complexity:
// O(n)
// (Queue used for level-order traversal.)


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
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        boolean seenNull = false;

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (curr == null) {
                seenNull = true;
            } else {
                if (seenNull) {
                    return false;
                }

                q.offer(curr.left);
                q.offer(curr.right);
            }
        }

        return true;
    }
}
