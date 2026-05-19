// Problem: Binary Tree Right Side View
// Link: https://leetcode.com/problems/binary-tree-right-side-view/
// Difficulty: Medium

// Approach:
// Use Level Order Traversal (BFS)
// to traverse the tree level by level.
// Store nodes of each level in queue.
// For every level:
//     - Traverse all nodes in that level.
//     - The last node processed in the level
//       represents the rightmost node.
//     - Add that node value to result.
// Push left and right children into queue
// for next level traversal.
// Return the final right side view list.

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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int level = q.size();

            for (int i = 0; i < level; i++) {
                TreeNode curr = q.poll();

                if (i == level - 1) {
                    res.add(curr.val);
                }

                if (curr.left != null) q.add(curr.left);
                if (curr.right != null)  q.add(curr.right);
            }
        }

        return res;
    }
}
