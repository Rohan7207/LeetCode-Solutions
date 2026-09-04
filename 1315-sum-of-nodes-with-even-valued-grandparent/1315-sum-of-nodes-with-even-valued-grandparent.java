// Problem: Sum of Nodes with Even-Valued Grandparent
// Link: https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
// Difficulty: Medium

// Approach:
// Use DFS + Parent and Grandparent Tracking.
//
// 1. Traverse the binary tree using DFS.
// 2. Pass the current node's parent and grandparent values as parameters.
//
// 3. For every node:
//      - If its grandparent is even, add the node's value to sum.
//
// 4. When moving to a child:
//      - The current node becomes the child's parent.
//      - The current node's parent becomes the child's grandparent.
//
// 5. Continue recursively for both left and right subtrees.
//
// 6. Return the accumulated sum after the traversal.

// Time Complexity: O(n)
// Space Complexity: O(h)
//      where h is the height of the tree.


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

    int sum = 0;

    public int sumEvenGrandparent(TreeNode root) {
        dfs(root, 1, 1);

        return sum;
    }

    private void dfs(TreeNode node, int parent, int grandParent) {
        if (node == null) {
            return;
        }

        if (grandParent % 2 == 0) {
            sum += node.val;
        }

        dfs(node.left, node.val, parent);
        dfs(node.right, node.val, parent);
    }
}
