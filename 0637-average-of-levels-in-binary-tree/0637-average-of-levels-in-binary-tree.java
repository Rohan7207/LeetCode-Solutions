// Problem: Average of Levels in Binary Tree
// Link: https://leetcode.com/problems/n-ary-tree-level-order-traversal/
// Difficulty: Easy

// Approach:
// Find the average value of nodes at each level
// of the binary tree.
// Use Level Order Traversal (BFS) because
// nodes must be processed level by level.
// Start by inserting the root node into
// the queue.
// While queue is not empty:
//     - Store queue size
//       This represents the number of nodes
//       in the current level.
//     - Traverse all nodes of that level
//     - Calculate the sum of node values
//     - Push left and right children into queue
// After processing the level:
//     Average = sum / number of nodes
//     Add the average to answer.
// Repeat until all levels are processed.

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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();

        if (root == null) return ans;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();

            double sum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();

                sum += curr.val;

                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
            }

            ans.add(sum / size);
        }

        return ans;
    }
}
