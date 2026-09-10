// Problem: Count Nodes Equal to Average of Subtree
// Link: https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/?envType=daily-question&envId=2026-09-10
// Difficulty: Medium

// Approach:
// Use Postorder DFS + Custom Return Object.
//
// 1. For every node, recursively get information from its left and
//    right subtrees.
//
// 2. Each DFS call returns an Info object containing:
//      - sum   → sum of all values in the subtree
//      - count → number of nodes in the subtree
//
// 3. Combine the left and right information with the current node:
//
//      sum   = left.sum + right.sum + root.val
//      count = left.count + right.count + 1
//
// 4. Calculate the average of the current subtree. If it equals
//    root.val, increment ans.
//
// 5. Return the current subtree's sum and count to its parent.
//
// 6. Since every node is processed once, this gives the answer
//    in a single DFS.

// Time Complexity: O(n)
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

    class Info {
        int sum;
        int count;

        Info(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);

        return ans;
    }

    private Info dfs(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }

        Info left = dfs(root.left);
        Info right = dfs(root.right);

        int sum = left.sum + right.sum + root.val;
        int count = left.count + right.count + 1;

        if ((sum / count) == root.val) {
            ans++;
        }

        return new Info(sum, count);
    }
}
