// Problem: Binary Tree Level Order Traversal
// Link: https://leetcode.com/problems/binary-tree-level-order-traversal/
// Difficulty: Medium

// Approach:
// Use Breadth First Search (BFS)
// with a queue to traverse the tree level by level.
// Add the root node into the queue.
// While the queue is not empty:
//     - Get the number of nodes in the current level.
//     - Traverse all nodes of that level.
//     - Remove node from queue and add its value
//       to the current level list.
//     - Add left and right child nodes to queue
//       if they exist.
// Add each level list to the final result.
// Return the level order traversal list.

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> all_list = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        if (root == null) {
            return all_list;
        }

        q.add(root);
        while (!q.isEmpty()) {
            int levels = q.size();
            List<Integer> sublevels = new ArrayList<>();
            for (int i = 0; i < levels; i++) {
                TreeNode curr = q.poll();
                sublevels.add(curr.val);
                if (curr.left != null)
                    q.add(curr.left);
                if (curr.right != null)
                    q.add(curr.right);
            }
            all_list.add(sublevels);
        }
        return all_list;
    }
}
