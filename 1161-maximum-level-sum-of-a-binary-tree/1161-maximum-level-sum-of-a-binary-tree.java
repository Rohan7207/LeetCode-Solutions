// Problem: Maximum Level Sum of a Binary Tree
// Link: https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
// Difficulty: Medium

// Approach:
// Use Breadth-First Search (BFS) / Level Order Traversal.
//
// 1. Put the root into a queue.
//
// 2. At each iteration, q.size() gives the number of nodes
//    belonging to the current level.
//
// 3. Process exactly `size` nodes:
//      - Add their values to `sum`.
//      - Add their left and right children to the queue.
//
// 4. After processing the complete level, compare its sum with
//    the maximum sum found so far.
//
// 5. If the current sum is greater, update:
//      - maxSum = current sum
//      - ans = current level
//
// 6. Continue until all levels are processed.
//
// 7. Return the level having the maximum sum.

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
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int maxSum = Integer.MIN_VALUE;
        int level = 1;
        int ans = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            int sum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                sum += curr.val;

                if (curr.left != null) {
                    q.offer(curr.left);
                }

                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }

            if (sum > maxSum) {
                ans = level;
                maxSum = sum;
            }

            level++;
        }
        
        return ans;
    }
}
